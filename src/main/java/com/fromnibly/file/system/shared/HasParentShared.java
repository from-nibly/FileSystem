package com.fromnibly.file.system.shared;

import com.fromnibly.file.system.entities.Entity;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.fromnibly.file.system.interfaces.HasParent;

/**
 * Created by jdavidson on 1/24/15.
 */
public class HasParentShared
{

  public static <T extends Entity & HasParent> void remove(T self)
      throws PathNotFoundException
  {
    self.getParent().getChildren().remove(self.getName());
    self.removeParent();
  }
}
