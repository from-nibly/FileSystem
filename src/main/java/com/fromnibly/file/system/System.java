package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Drive;
import com.fromnibly.file.system.entities.Entity;
import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.NotATextFileException;
import com.fromnibly.file.system.exceptions.PathAlreadyExistsException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by jdavidson on 1/22/15.
 */
public class System
{
  private final Map<String, Drive> drives;

  public System()
  {
    drives = Maps.newHashMap();
  }

  public void mount(String name) throws IllegalFileSystemOperationException
  {
    drives.put(name, new Drive(name));
  }

  //Methods to implement for challenge

  /**
   * creates an entity of a type at a path named something
   *
   * @param type         the type of entity: Folder, File, or ZipFile
   * @param name         the name of the new entity
   * @param pathOfParent the path of where the new entity should end up.
   * @throws PathNotFoundException               if the path does not exist
   * @throws PathAlreadyExistsException          if the entity already exists
   * @throws IllegalFileSystemOperationException if something else happens
   */
  public void create(Class<? extends Entity> type, String name, String pathOfParent)
      throws PathNotFoundException, PathAlreadyExistsException, IllegalFileSystemOperationException
  {
    List<String> path = Lists.newArrayList(pathOfParent.split("\\\\"));
    Drive d = getDriveModifyPath(path);
    d.addChild(type, name, path);
  }

  /**
   * Writes content to an existing file.
   *
   * @param pathToFile the path including the file name
   * @param content    the content to write to the file
   * @throws PathNotFoundException if there is no such file
   * @throws NotATextFileException if the path leads to a file that is not a text file
   */
  public void writeToFile(String pathToFile, String content) throws PathNotFoundException,
      NotATextFileException
  {
    List<String> path = Lists.newArrayList(pathToFile.split("\\\\"));
    Drive d = getDriveModifyPath(path);
    d.setContent(path, content);
  }

  /**
   * deletes a file
   *
   * @param pathToFile the path including the file name
   * @throws PathNotFoundException if the path or file does not exist
   */
  public void delete(String pathToFile) throws PathNotFoundException
  {
    List<String> path = Lists.newArrayList(pathToFile.split("\\\\"));
    Drive d = getDriveModifyPath(path);
    d.remove(path);
  }

  /**
   * gets the size of a file or all of the files within a path
   *
   * @param pathToFile path to a file or folder
   * @return the size of a file or all of the files within a path
   * @throws PathNotFoundException if the path or file does not exist
   */
  public int getSize(String pathToFile) throws PathNotFoundException
  {
    List<String> path = Lists.newArrayList(pathToFile.split("\\\\"));
    Drive d = getDriveModifyPath(path);
    return d.find(path).getSize();
  }

  //end methods to implement for challenge

  /**
   * checks to see if a file exists
   *
   * @param pathToFile path to file including the file name
   * @throws PathNotFoundException if the path or file is not found
   */
  public void find(String pathToFile) throws PathNotFoundException
  {
    List<String> path = Lists.newArrayList(pathToFile.split("\\\\"));
    Drive d = getDriveModifyPath(path);
    d.find(path);
  }

  private Drive getDriveModifyPath(final List<String> path) throws PathNotFoundException
  {
    if (path.get(path.size() - 1) == "")
    {
      path.remove(path.size() - 1);
    }
    if (path.size() == 0)
    {
      throw new PathNotFoundException();
    }
    String driveName = path.get(0).replace(":", "");
    if (path.size() > 1)
    {
      path.remove(0);
    }
    else
    {
      path.clear();
    }

    Drive d = drives.get(driveName);

    if (d != null)
    {
      return d;
    }
    else
    {
      throw new PathNotFoundException();
    }
  }

}
