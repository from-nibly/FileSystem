package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Drive;
import com.fromnibly.file.system.entities.TextFile;
import com.fromnibly.file.system.entities.ZipFile;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jdavidson on 1/22/15.
 */
public class ZipFileTest
{
  @Test
  public void zipFile_getPath_returnsPath() throws Exception
  {
    ZipFile file = new ZipFile("test", new Drive("C"));
    assertEquals("C:\\test", file.getPath());
  }

  @Test
  public void zipFile_getName_returnsOnlyName() throws Exception
  {
    ZipFile file = new ZipFile("test", null);
    assertEquals("test", file.getName());
  }

  @Test
  public void zipFile_setContent_getContent_returnsSame() throws Exception
  {
    ZipFile file = new ZipFile("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    String content = file.getContent(Lists.newArrayList("testing"));
    assertEquals("testing123", content);
  }

  @Test
  public void zipFile_getSize_returnsHalfContentSize() throws Exception
  {
    ZipFile file = new ZipFile("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    int size = file.getSize();
    assertEquals(5, size);
  }

  @Test(expected = PathNotFoundException.class)
  public void zipFile_fileNotFound_throwsException() throws Exception
  {
    ZipFile file = new ZipFile("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.setContent(Lists.newArrayList("garble"), "testing123");
  }

  @Test
  public void zipFile_multipleChildren_getSize_returnsCorrectSize() throws Exception
  {
    ZipFile file = new ZipFile("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.addChild(TextFile.class, "testing2", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    file.setContent(Lists.newArrayList("testing2"), "testing123");
    int size = file.getSize();
    assertEquals(10, size);
  }

  @Test
  public void zipFile_deleteChild_removesFile() throws Exception
  {
    ZipFile file = new ZipFile("test", null);
    file.addChild(TextFile.class, "testing", Lists.newArrayList());
    file.addChild(TextFile.class, "testing2", Lists.newArrayList());
    file.setContent(Lists.newArrayList("testing"), "testing123");
    file.setContent(Lists.newArrayList("testing2"), "testing1234");
    file.remove(Lists.newArrayList("testing"));
    int size = file.getSize();
    assertEquals(5, size);
  }

}
