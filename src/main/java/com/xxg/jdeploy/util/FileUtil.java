/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.xxg.jdeploy.util;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

/**
 * File Utilities
 *
 */
public class FileUtil {

    public static final String module = FileUtil.class.getName();

    /**
     * Writes a file from a string with a specified encoding.
     *
     * @param path
     * @param name
     * @param encoding
     * @param s
     * @throws IOException
     */
    public static void writeString(String path, String name, String encoding, String s) throws IOException {
        String fileName = getPatchedFileName(path, name);
        if (UtilValidate.isEmpty(fileName)) {
            throw new IOException("Cannot obtain buffered writer for an empty filename!");
        }

        try {
            FileUtils.writeStringToFile(new File(fileName), s, encoding);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void writeString(String encoding, String s, File outFile) throws IOException {
        try {
            FileUtils.writeStringToFile(outFile, s, encoding);
        } catch (IOException e) {
             throw e;
        }
    }

    public static Writer getBufferedWriter(String path, String name) throws IOException {
        String fileName = getPatchedFileName(path, name);
        if (UtilValidate.isEmpty(fileName)) {
            throw new IOException("Cannot obtain buffered writer for an empty filename!");
        }

        return new BufferedWriter(new FileWriter(fileName));
    }

    public static OutputStream getBufferedOutputStream(String path, String name) throws IOException {
        String fileName = getPatchedFileName(path, name);
        if (UtilValidate.isEmpty(fileName)) {
            throw new IOException("Cannot obtain buffered writer for an empty filename!");
        }

        return new BufferedOutputStream(new FileOutputStream(fileName));
    }

    public static String getPatchedFileName(String path, String fileName) throws IOException {
        // make sure the export directory exists
        if (UtilValidate.isNotEmpty(path)) {
            path = path.replaceAll("\\\\", "/");
            File parentDir = new File(path);
            if (!parentDir.exists()) {
                if (!parentDir.mkdir()) {
                    throw new IOException("Cannot create directory for path: " + path);
                }
            }

            // build the filename with the path
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            if (fileName.startsWith("/")) {
                fileName = fileName.substring(1);
            }
            fileName = path + fileName;
        }

        return fileName;
    }

    public static StringBuffer readTextFile(File file, boolean newline) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        StringBuffer buf = new StringBuffer();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            //in = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.toCharset("UTF-8")));
            //in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            //InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            //System.out.println("File Encoding:["+ read.getEncoding());

            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
                if (newline) {
                    buf.append(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }

        return buf;
    }

    /**
     * 获取文件编码
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getFileEncoding(String fileName) throws IOException {
        InputStreamReader read = null;
        String encoding = "";
        try{
            read = new InputStreamReader(new FileInputStream(new File(fileName)));
            encoding = read.getEncoding();
            read.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        return encoding;
    }

    public static StringBuffer readTextFile(String fileName, boolean newline) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        return readTextFile(file, newline);
    }
    public static String readString(String encoding, File inFile) throws IOException {
        String readString = "";
        try {
            readString = FileUtils.readFileToString(inFile, encoding);
        } catch (IOException e) {
            throw e;
        }
        return readString;
    }

    public static void searchFiles(List<File> fileList, File path, FilenameFilter filter, boolean includeSubfolders) throws IOException {
        // Get filtered files in the current path
        File[] files = path.listFiles(filter);
        if (files == null) {
            return;
        }

        // Process each filtered entry
        for (int i = 0; i < files.length; i++) {
            // recurse if the entry is a directory
            if (files[i].isDirectory() && includeSubfolders && !files[i].getName().startsWith(".")) {
                searchFiles(fileList, files[i], filter, true);
            } else {
                // add the filtered file to the list
                fileList.add(files[i]);
            }
        }
    }

       /**
    *
    *
    * Search for the specified <code>searchString</code> in the given
    * {@link Reader}.
    *
    * @param reader A Reader in which the String will be searched.
    * @param searchString The String to search for
    * @return <code>TRUE</code> if the <code>searchString</code> is found;
    *         <code>FALSE</code> otherwise.
    * @throws IOException
    */
   public static boolean containsString(Reader reader, final String searchString) throws IOException {
       char[] buffer = new char[1024];
       int numCharsRead;
       int count = 0;
       while((numCharsRead = reader.read(buffer)) > 0) {
           for (int c = 0; c < numCharsRead; ++c) {
               if (buffer[c] == searchString.charAt(count)) count++;
               else count = 0;
               if (count == searchString.length()) return true;
           }
       }
       return false;
   }

   /**
    *
    *
    * Search for the specified <code>searchString</code> in the given
    * filename. If the specified file doesn't exist, <code>FALSE</code>
    * returns.
    *
    * @param fileName A full path to a file in which the String will be searched.
    * @param searchString The String to search for
    * @return <code>TRUE</code> if the <code>searchString</code> is found;
    *         <code>FALSE</code> otherwise.
    * @throws IOException
    */
   public static boolean containsString(final String fileName, final String searchString) throws IOException {
       File inFile = new File(fileName);
       if (inFile.exists()) {
           BufferedReader in = new BufferedReader(new FileReader(inFile));
           try {
               return containsString(in, searchString);
           } finally {
               if (in != null)in.close();
           }
       } else {
           return false;
       }
   }

    /**
     * 复制一个目录及其子目录、文件到另外一个目录
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // 递归复制
                copyFolder(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    /**
     * 移动一个文件到指定目录
     * @param oldFile
     * @param newDir
     * @throws IOException
     */
    public static void moveFile(File oldFile,String newDir) throws IOException{
        File newFolder = new File(newDir);
        if(!newFolder.exists())
            newFolder.mkdirs();
        File newFile = new File(newDir + oldFile.getName());
        oldFile.renameTo(newFile);
    }

}
