package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Folder;
import com.fromnibly.file.system.entities.TextFile;
import com.fromnibly.file.system.entities.ZipFile;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by jdavidson on 1/24/15.
 */
public class SystemTest
{

  @Test
  public void system_create_works() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(TextFile.class, "testing", "C:\\");
    s.find("C:\\testing");
  }

  @Test
  public void system_create_inFolder_works() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(Folder.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.find("C:\\test\\testing");
  }

  @Test(expected = PathNotFoundException.class)
  public void system_find_incorrectPath_throws() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(Folder.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.find("C:\\testing");
  }

  @Test
  public void system_getSize_inFolder_works() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(Folder.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.writeToFile("C:\\test\\testing", "content");
    int size = s.getSize("C:\\");
    assertEquals(7, size);
  }

  @Test
  public void system_create_inZip_works() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(ZipFile.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.find("C:\\test\\testing");
  }

  @Test
  public void system_delete_works() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(Folder.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.find("C:\\test\\testing");
    s.delete("C:\\test\\testing");

    try
    {
      s.find("C:\\test\\testing");
      fail();
    }
    catch (PathNotFoundException e)
    {
    }
  }

  @Test(expected = PathNotFoundException.class)
  public void system_delete_nonExistentFile_throws() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(Folder.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.find("C:\\test\\testing");
    s.delete("C:\\test\\testing123");
  }

  @Test
  public void system_delete_folder_removesFolder() throws Exception
  {
    System s = new System();
    s.mount("C");
    s.create(Folder.class, "test", "C:\\");
    s.create(TextFile.class, "testing", "C:\\test\\");
    s.find("C:\\test\\testing");
    s.delete("C:\\test");

    try
    {
      s.find("C:\\test");
      fail();
    }
    catch (PathNotFoundException e)
    {

    }
  }

}
