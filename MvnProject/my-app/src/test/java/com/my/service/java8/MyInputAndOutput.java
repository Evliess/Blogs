package com.my.service.java8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;


public class MyInputAndOutput {

  String resources =
      "." + File.separator + "src" + File.separator + "test" + File.separator + "resources"
          + File.separator;

  @Test
  public void files() {
    try {
      Path path = Paths.get(resources + "test.text");
      byte[] bytes = Files.readAllBytes(path);
      System.out.println(bytes.length);
      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      lines.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void scanner() {
    try {
      Path path = Paths.get(resources + "test.text");
      Scanner scanner = new Scanner(path, "UTF-8");
      scanner.useDelimiter("\\PL+");
      while (scanner.hasNext()) {
        System.out.println(scanner.next());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Test
  public void paths() throws Exception {
    Path path = Paths.get(resources + "file.txt");
    System.out.println(path.toAbsolutePath());

  }

  /**
   * 1. PrintWriter out 2. try (PrintWriter out = ...) 3. Files.write(path,contentString.getBytes(charset));
   * 4. Files.write(path, lines, charset) 5. Files.write(path, lines, charset,
   * StandardOpenOption.APPEND);
   */
  @Test
  public void printerWriter() {
    Path path = Paths.get(resources + "file.txt");
    try {
      PrintWriter out = new PrintWriter(path.toAbsolutePath().toFile(), "UTF-8");
      out.println("This is file1.txt");
      out.println(
          "Never forget what you are, for surely the world will not. Make it your strength.");
      out.close();
      Files.delete(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void write() {
    try {
      Path file1Path = Paths.get(resources + "file1.txt");
      String content = "Never forget what you are, for surely the world will not. Make it your strength.";
      Files.write(file1Path, content.getBytes("UTF-8"));
      Files.delete(file1Path);

      List<String> lines = Arrays.asList(
          "Some war against sword and spear to win, and the others the crow and the paper to win.",
          "The more you love, the weaker you are.",
          "The lies we tell for love. May the gods forgive me.");
      Path file2Path = Paths.get(resources + "file2.txt");
      Files.write(file2Path, lines);
      Files.delete(file2Path);

      Path file3Path = Paths.get(resources + "file3.txt");
      Files.write(file3Path, lines, StandardOpenOption.CREATE);
      Files.delete(file3Path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void exist() {
    Path create = Paths.get(resources + "create.txt");
    try {
      if (!Files.exists(create)) {
        Files.createFile(create);
      }
      System.out.println("File Size: " + Files.size(create));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assert.assertTrue(Files.exists(create));
    Assert.assertFalse(Files.isDirectory(create));
    Assert.assertTrue(Files.isRegularFile(create));
    Assert.assertFalse(Files.isSymbolicLink(create));


  }

  @Test
  public void copy() {
    Path form = Paths.get(resources + "test.text");
    Path to = Paths.get(resources + "test.text.bk");
    try {
      Files.deleteIfExists(to);
      Files.copy(form, to);
//      Files.move(form, to);
      Files.delete(to);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void walk() {
    try {
      //Copy a directory tree
      Files.walk(Paths.get(resources));

      // Delete a directory tree
      Files.walkFileTree(Paths.get(resources), new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          Files.delete(file);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
          if (null != exc) {
            throw exc;
          }
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
