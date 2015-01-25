package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Drive;
import com.fromnibly.file.system.entities.Folder;
import com.fromnibly.file.system.entities.TextFile;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jdavidson on 1/23/15.
 */
public class FolderTest
{
  @Test
  public void folder_getPath_returnsPath() throws Exception
  {
    Folder file = new Folder("test", new Drive("C"));
    assertEquals("C:\\test\\", file.getPath());
  }

  @Test
  public void folder_getName_returnsOnlyName() throws Exception
  {
    Folder file = new Folder("test", null);
    assertEquals("test", file.getName());
  }

  @Test
  public void folder_setContent_getContent_returnsSame() throws Exception
  {
    Folder file = new Folder("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    String content = file.getContent(Lists.newArrayList("testing"));
    assertEquals("testing123", content);
  }

  @Test
  public void folder_getSize_returnsContentSize() throws Exception
  {
    Folder file = new Folder("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    int size = file.getSize();
    assertEquals(10, size);
  }

  @Test(expected = PathNotFoundException.class)
  public void folder_fileNotFound_throwsException() throws Exception
  {
    Folder file = new Folder("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("garble"), "testing123");
  }

  @Test
  public void folder_multipleChildren_getSize_returnsCorrectSize() throws Exception
  {
    Folder file = new Folder("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.addChild(TextFile.class, "testing2", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    file.setContent(Lists.newArrayList("testing2"), "testing1234");
    int size = file.getSize();
    assertEquals(21, size);
  }

  @Test
  public void folder_deleteChild_removesFile() throws Exception
  {
    Folder file = new Folder("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.addChild(TextFile.class, "testing2", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    file.setContent(Lists.newArrayList("testing2"), "testing1234");
    file.remove(Lists.newArrayList("testing"));
    int size = file.getSize();
    assertEquals(11, size);
  }

}
