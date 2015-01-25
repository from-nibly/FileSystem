package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Entity;
import com.fromnibly.file.system.entities.Folder;
import com.fromnibly.file.system.entities.TextFile;
import com.fromnibly.file.system.entities.ZipFile;
import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;

/**
 * Created by jdavidson on 1/22/15.
 */
public class EntityFactory
{

  /**
   * creates an entity based on the type
   *
   * @param type   the type of entity to be created
   * @param name   the name of the entity
   * @param parent the parent to be attached to the entity
   * @return returns a linked entity
   * @throws IllegalFileSystemOperationException if an unknown type is specified
   */
  public static Entity create(Class<? extends Entity> type, String name, Entity parent)
      throws IllegalFileSystemOperationException
  {
    if (type.equals(Folder.class))
    {
      return new Folder(name, parent);
    }
    else if (type.equals(TextFile.class))
    {
      return new TextFile(name, parent);
    }
    else if (type.equals(ZipFile.class))
    {
      return new ZipFile(name, parent);
    }
    else
    {
      throw new IllegalFileSystemOperationException("unknown file type");
    }
  }
}
