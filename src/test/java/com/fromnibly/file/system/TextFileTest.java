package com.fromnibly.file.system;

import com.fromnibly.file.system.entities.Drive;
import com.fromnibly.file.system.entities.TextFile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jdavidson on 1/22/15.
 */
public class TextFileTest
{

  @Test
  public void textFile_getPath_returnsPath() throws Exception
  {
    TextFile file = new TextFile("test", new Drive("C"));
    assertEquals("C:\\test", file.getPath());
  }

  @Test
  public void textFile_getName_returnsOnlyName() throws Exception
  {
    TextFile file = new TextFile("test", null);
    assertEquals("test", file.getName());
  }

  @Test
  public void textFile_setContent_getContent_returnsSame() throws Exception
  {
    TextFile file = new TextFile("test", null);
    file.setContent("stuff");
    assertEquals("stuff", file.getContent());
  }

  @Test
  public void textFile_getSize_returnsContentSize() throws Exception
  {
    TextFile file = new TextFile("test", null);
    file.setContent("stuff");
    assertEquals(5, file.getSize());
  }

  @Test
  public void textFile_getType_returnsTextFile() throws Exception
  {
    TextFile file = new TextFile("test", null);
    assertEquals("TextFile", file.getType());
  }

}
