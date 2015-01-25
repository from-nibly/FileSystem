package com.fromnibly.file.system.shared;

import com.fromnibly.file.system.EntityFactory;
import com.fromnibly.file.system.entities.Entity;
import com.fromnibly.file.system.entities.File;
import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.NotATextFileException;
import com.fromnibly.file.system.exceptions.PathAlreadyExistsException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.fromnibly.file.system.interfaces.HasChildren;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by jdavidson on 1/22/15.
 * used to accomplish multiple inheritance and consolidate functionality between things that implement HasChildren
 */
public class HasChildrenShared
{
  /**
   * adds a child entity to the path
   *
   * @param self the object being interacted with
   * @param type the type of entity to add
   * @param name the name of the entity
   * @param path the path excluding the entity to add
   * @throws PathNotFoundException               if the path does not exist
   * @throws IllegalFileSystemOperationException if the type is invalid
   * @throws PathAlreadyExistsException          if an entity already exists at the given path and name
   */
  public static <T extends Entity & HasChildren> void addChild(T self, Class<? extends Entity> type,
      String name, List<String> path)
      throws PathNotFoundException, IllegalFileSystemOperationException, PathAlreadyExistsException
  {
    Entity e = traverse(self, path);
    if (e instanceof HasChildren)
    {
      if (((HasChildren) e).getChildren().containsKey(name))
      {
        throw new PathAlreadyExistsException();
      }
      ((HasChildren) e).getChildren().put(name, EntityFactory.create(type, name, e));
    }
    else
    {
      throw new PathNotFoundException();
    }
  }

  /**
   * sets the content of a TextFile
   *
   * @param self    the object being interacted with
   * @param path    the path including the file
   * @param content the content to be written to the file
   * @throws PathNotFoundException if the path does not exist
   * @throws NotATextFileException if the entity is not a text file
   */
  public static <T extends Entity & HasChildren> void setContent(T self, List<String> path,
      String content) throws PathNotFoundException, NotATextFileException
  {
    Entity e = traverse(self, path);
    if (e instanceof File)
    {
      ((File) e).setContent(content);
    }
    else
    {
      throw new NotATextFileException();
    }
  }

  public static <T extends Entity & HasChildren> Entity traverse(T self,
      List<String> path) throws PathNotFoundException
  {
    if (path.size() > 0)
    {
      Entity child = self.getChildren().get(path.get(0));
      if (child == null)
      {
        throw new PathNotFoundException();
      }
      if (path.size() > 1)
      {
        return child.traverse(path.subList(1, path.size()));
      }
      else
      {
        return child;
      }
    }
    else
    {
      return self;
    }
  }

  /**
   * gets the content of a file
   *
   * @param self the object being interacted with
   * @param path the path including the file
   * @return returns the content of a text file
   * @throws PathNotFoundException if the path or file does not exist
   * @throws NotATextFileException if the entity is not a text file
   */
  public static <T extends Entity & HasChildren> String getContent(T self, List<String> path)
      throws PathNotFoundException, NotATextFileException
  {
    AtomicReference<String> rtnContent = new AtomicReference<String>();
    Entity e = traverse(self, path);
    if (e instanceof File)
    {
      rtnContent.set(((File) e).getContent());
    }
    else
    {
      throw new NotATextFileException();
    }
    return rtnContent.get();
  }

  public static <T extends Entity & HasChildren> void removeChild(T self, List<String> path)
      throws PathNotFoundException
  {
    if (path.size() > 0)
    {
      Entity e = traverse(self, path);
      e.remove(Lists.newArrayList());
    }
    else
    {
      for (Map.Entry<String, Entity> e : self.getChildren().entrySet())
      {
        e.getValue().remove(Lists.newArrayList());
      }
    }
  }

  /**
   * checks to see if a file exists
   *
   * @param self the object being interacted with
   * @param path path to file including the file name
   * @throws PathNotFoundException if the path or file is not found
   */
  public static <T extends Entity & HasChildren> Entity find(T self, List<String> path)
      throws PathNotFoundException
  {
    if (path.size() > 0)
    {
      Entity child = self.getChild(path.get(0));
      if (child == null)
      {
        throw new PathNotFoundException();
      }
      if (path.size() > 1)
      {
        if (child instanceof HasChildren)
        {
          return ((HasChildren) child).find(path.subList(1, path.size()));
        }
        else
        {
          throw new PathNotFoundException();
        }
      }
      else
      {
        return child;
      }
    }
    else
    {
      return self;
    }
  }
}
