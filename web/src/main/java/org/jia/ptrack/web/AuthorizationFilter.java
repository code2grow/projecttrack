/**
   JavaServer Faces in Action example code, Copyright (C) 2004 Kito D. Mann.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

**/

package org.jia.ptrack.web;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import org.jia.ptrack.domain.RoleType;

public class AuthorizationFilter implements Filter
{
  FilterConfig config = null;
  ServletContext servletContext = null;

  public AuthorizationFilter()
  {
  }

  public void init(FilterConfig filterConfig) throws ServletException
  {
    config = filterConfig;
    servletContext = config.getServletContext();
  }

  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException
  {
    Utils.log(servletContext, "Inside the filter");

    HttpServletRequest httpRequest = (HttpServletRequest)request;
    HttpServletResponse httpResponse = (HttpServletResponse)response;
    HttpSession session = httpRequest.getSession();

    String requestPath = httpRequest.getPathInfo();
    Visit visit = (Visit)session.getAttribute(Constants.VISIT_KEY);
    if (visit == null)
    {
      session.setAttribute(Constants.ORIGINAL_VIEW_KEY, httpRequest.getPathInfo());
      Utils.log(servletContext, "redirecting to " + httpRequest.getContextPath() +
                Constants.LOGIN_VIEW);
      httpResponse.sendRedirect(httpRequest.getContextPath() +
                                Constants.LOGIN_VIEW);
    }
    else
    {
      session.removeAttribute(Constants.ORIGINAL_VIEW_KEY);
      RoleType role = visit.getUser().getRole();

      if ((role.equals(RoleType.UPPER_MANAGER) &&
           requestPath.indexOf(Constants.PROTECTED_DIR) > 0) ||
          (!role.equals(RoleType.PROJECT_MANAGER) &&
           requestPath.indexOf(Constants.EDIT_DIR) > 0))
      {
        String text = Utils.getDisplayString(Constants.BUNDLE_BASENAME,
                                             "PathNotFound",
                                             new Object[] { requestPath },
                                             request.getLocale());
        httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND,
                               text);
      }
      else
      {
        chain.doFilter(request, response);
      }
    }
    Utils.log(servletContext, "Exiting the filter");
  }

  public void destroy()
  {
  }
}
