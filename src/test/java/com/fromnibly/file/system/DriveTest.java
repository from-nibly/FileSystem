package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Drive;
import com.fromnibly.file.system.entities.TextFile;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jdavidson on 1/24/15.
 */
public class DriveTest
{

  @Test
  public void drive_getPath_returnsPath() throws Exception
  {
    Drive file = new Drive("C");
    assertEquals("C:\\", file.getPath());
  }

  @Test
  public void drive_getName_returnsOnlyName() throws Exception
  {
    Drive file = new Drive("C");
    assertEquals("C", file.getName());
  }

  @Test
  public void drive_setContent_getContent_returnsSame() throws Exception
  {
    Drive file = new Drive("test");
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    String content = file.getContent(Lists.newArrayList("testing"));
    assertEquals("testing123", content);
  }

  @Test
  public void drive_getSize_returnsContentSize() throws Exception
  {
    Drive file = new Drive("test");
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    int size = file.getSize();
    assertEquals(10, size);
  }

  @Test(expected = PathNotFoundException.class)
  public void drive_fileNotFound_throwsException() throws Exception
  {
    Drive file = new Drive("test");
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("garble"), "testing123");
  }

  @Test
  public void drive_multipleChildren_getSize_returnsCorrectSize() throws Exception
  {
    Drive file = new Drive("test");
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.addChild(TextFile.class, "testing2", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    file.setContent(Lists.newArrayList("testing2"), "testing1234");
    int size = file.getSize();
    assertEquals(21, size);
  }

  @Test
  public void drive_deleteChild_removesFile() throws Exception
  {
    Drive file = new Drive("test");
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.addChild(TextFile.class, "testing2", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    file.setContent(Lists.newArrayList("testing2"), "testing1234");
    file.remove(Lists.newArrayList("testing"));
    int size = file.getSize();
    assertEquals(11, size);
  }

}
