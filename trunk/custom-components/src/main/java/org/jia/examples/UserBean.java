package org.jia.examples;

import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class UserBean implements Serializable
{

  private String firstName;
  private String password;
  private String lastName;
  private double balance;
  private java.util.Date dateOfBirth;
  private int numberOfVisits;
  private String favoriteAnimal;
  private boolean registered;
  private String[] selectedAnimals;
  private java.util.List favoriteSites;
  private String[] favoriteSites2;
  private java.util.List favoriteNumbers;
  private java.util.Map favoriteSitesMap;
  private java.util.Map favoriteNumbersMap;

  public UserBean()
  {
    setFirstName("Joe");
    setLastName("Funk");
    setPassword("test");
    setDateOfBirth(new java.util.Date());
    setBalance(4029.3456);
    setNumberOfVisits(243000);
    setFavoriteAnimal(null);
    setSelectedAnimals(null);

    favoriteSites = new ArrayList();
    favoriteSites.add("www.yahoo.com");
    favoriteSites.add("www.javalobby.org");
    favoriteSites.add("www.jsfcentral.com");
    favoriteNumbers = new Vector();

    favoriteSitesMap = new TreeMap();
    favoriteSitesMap.put("foo", "bar");
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getFirstName()
  {
    return firstName;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  public String getPassword()
  {
    return password;
  }
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  public String getLastName()
  {
    return lastName;
  }
  public void setBalance(double balance)
  {
    this.balance = balance;
  }
  public double getBalance()
  {
    return balance;
  }
  public void setDateOfBirth(java.util.Date dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }
  public java.util.Date getDateOfBirth()
  {
    return dateOfBirth;
  }
  public void setNumberOfVisits(int numberOfVisits)
  {
    this.numberOfVisits = numberOfVisits;
  }
  public int getNumberOfVisits()
  {
    return numberOfVisits;
  }
  public void setSelectedAnimals(String[] selectedAnimals)
  {
    this.selectedAnimals = selectedAnimals;
  }
  public String[] getSelectedAnimals()
  {
    return selectedAnimals;
  }
  public void setFavoriteAnimal(String favoriteAnimal)
  {
    this.favoriteAnimal = favoriteAnimal;
  }
  public String getFavoriteAnimal()
  {
    return favoriteAnimal;
  }
  public boolean isRegistered()
  {
    return registered;
  }
  public void setRegistered(boolean registered)
  {
    this.registered = registered;
  }
  public java.util.List getFavoriteSites()
  {
    return favoriteSites;
  }
  public void setFavoriteSites(java.util.List favoriteSites)
  {
    this.favoriteSites = favoriteSites;
  }
  public String[] getFavoriteSites2()
  {
    return favoriteSites2;
  }
  public void setFavoriteSites2(String[] favoriteSites2)
  {
    this.favoriteSites2 = favoriteSites2;
  }
  public java.util.List getFavoriteNumbers()
  {
    return favoriteNumbers;
  }
  public void setFavoriteNumbers(java.util.List favoriteNumbers)
  {
    this.favoriteNumbers = favoriteNumbers;
  }
  public java.util.Map getFavoriteSitesMap()
  {
    return favoriteSitesMap;
  }
  public void setFavoriteSitesMap(java.util.Map favoriteSitesMap)
  {
    this.favoriteSitesMap = favoriteSitesMap;
  }
  public java.util.Map getFavoriteNumbersMap()
  {
    return favoriteNumbersMap;
  }
  public void setFavoriteNumbersMap(java.util.Map favoriteNumbersMap)
  {
    this.favoriteNumbersMap = favoriteNumbersMap;
  }
}
