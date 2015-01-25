package com.fromnibly.file.system.interfaces;

import com.fromnibly.file.system.entities.Entity;
import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.NotATextFileException;
import com.fromnibly.file.system.exceptions.PathAlreadyExistsException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Created by jdavidson on 1/22/15.
 */
public interface HasChildren
{
  public Map<String, Entity> getChildren();

  /**
   * adds a child entity to the path
   *
   * @param type the type of entity to add
   * @param name the name of the entity
   * @param path the path excluding the entity to add
   * @throws PathNotFoundException               if the path does not exist
   * @throws IllegalFileSystemOperationException if the type is invalid
   * @throws PathAlreadyExistsException          if an entity already exists at the given path and name
   */
  public void addChild(Class<? extends Entity> type, String name, List<String> path)
      throws PathNotFoundException, IllegalFileSystemOperationException, PathAlreadyExistsException;

  /**
   * returns a child with the name
   *
   * @param name the name required
   * @return a child with the name or null if no child exists
   */
  default public Entity getChild(String name)
  {
    return getChildren().get(name);
  }

  /**
   * returns true if the child exists
   *
   * @param name the name of the child in question
   * @return returns true if the child exists false otherwise
   */
  default public boolean hasChild(String name)
  {
    return getChildren().containsKey(name);
  }

  /**
   * sets the content of a TextFile
   *
   * @param path    the path including the file
   * @param content the content to be written to the file
   * @throws PathNotFoundException if the path does not exist
   * @throws NotATextFileException if the entity is not a text file
   */
  public void setContent(List<String> path, String content)
      throws PathNotFoundException, NotATextFileException;

  /**
   * gets the content of a file
   *
   * @param path the path including the file
   * @return returns the content of a text file
   * @throws PathNotFoundException if the path or file does not exist
   * @throws NotATextFileException if the entity is not a text file
   */
  public String getContent(List<String> path)
      throws IllegalFileSystemOperationException, PathNotFoundException, NotATextFileException;

  /**
   * checks to see if a file exists
   *
   * @param path path to file including the file name
   * @throws PathNotFoundException if the path or file is not found
   */
  public Entity find(List<String> path) throws PathNotFoundException;

}
