package com.chainspower.mytab;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * Description:
 * Date：2019/2/18-10:49
 * Author: cwh
 */
public class Nio {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        simpleChannel();
        transferFrom();
        transferTo();
        try {
            pipe();
        } catch (IOException e) {
            e.printStackTrace();
        }

        path();

        files();


        try {
            fileStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            filePropery();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            filePermission();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("===========================================");
        try {
            asyncFileChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void simpleChannel() {
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        try {
            randomAccessFile = new RandomAccessFile("D:\\AndroidProject\\MyTab\\app\\src\\3443588.lrc", "rw");
            fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int readLen = fileChannel.read(byteBuffer);
            while (readLen != -1) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                String string = new String(bytes, "utf-8");
                System.out.print(string);
                byteBuffer.clear();
                readLen = fileChannel.read(byteBuffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }


    public static void transferFrom() {
        RandomAccessFile randomAccessFileFrom = null;
        RandomAccessFile randomAccessFileTo = null;
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            randomAccessFileFrom = new RandomAccessFile("D:\\AndroidProject\\MyTab\\app\\src\\3814673.lrc", "rw");
            randomAccessFileTo = new RandomAccessFile("D:\\AndroidProject\\MyTab\\app\\src\\transfer_from.txt", "rw");
            fromChannel = randomAccessFileFrom.getChannel();
            toChannel = randomAccessFileTo.getChannel();
            toChannel.transferFrom(fromChannel, 0, randomAccessFileFrom.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFileFrom != null) {
                try {
                    randomAccessFileFrom.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (randomAccessFileTo != null) {
                try {
                    randomAccessFileTo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fromChannel != null) {
                try {
                    fromChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (toChannel != null) {
                try {
                    toChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void transferTo() {
        RandomAccessFile randomAccessFileFrom = null;
        RandomAccessFile randomAccessFileTo = null;
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            randomAccessFileFrom = new RandomAccessFile("D:\\AndroidProject\\MyTab\\app\\src\\transfer_from.txt", "rw");
            randomAccessFileTo = new RandomAccessFile("D:\\AndroidProject\\MyTab\\app\\src\\transfer_to.txt", "rw");
            fromChannel = randomAccessFileFrom.getChannel();
            toChannel = randomAccessFileTo.getChannel();
            fromChannel.transferTo(0, randomAccessFileFrom.length(), toChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFileFrom != null) {
                try {
                    randomAccessFileFrom.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (randomAccessFileTo != null) {
                try {
                    randomAccessFileTo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fromChannel != null) {
                try {
                    fromChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (toChannel != null) {
                try {
                    toChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //可用于两个现场间的单向的交互
    public static void pipe() throws IOException {
        final Pipe pipe = Pipe.open();
        new Thread() {
            @Override
            public void run() {
                super.run();
                Pipe.SinkChannel sinkChannel = pipe.sink();
                String text = "Hello World!!";
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.clear();
                byteBuffer.put(text.getBytes());
                byteBuffer.flip();
                try {
                    while (byteBuffer.hasRemaining()) {
                        sinkChannel.write(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        sinkChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                Pipe.SourceChannel sourceChannel = pipe.source();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.clear();
                try {
                    while (sourceChannel.read(byteBuffer) != -1) {
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        String string = new String(bytes, "utf-8");
                        System.out.println("Pipe String is:" + string);
                        byteBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void path() {
        Path path1 = Paths.get("D:\\AndroidProject\\MyTab\\app", ".\\test");
        Path path2 = Paths.get("D:\\AndroidProject\\MyTab\\app", "..\\test1");
        System.out.println("path1 is :" + path1.normalize().toAbsolutePath());
        System.out.println("path2 is :" + path2.normalize().toAbsolutePath());

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void files() {
        Path path1 = Paths.get("D:\\AndroidProject\\MyTab\\app", ".\\test");
        Path path2 = Paths.get("D:\\AndroidProject\\MyTab\\app", "..\\test1");
        if (!Files.exists(path1)) {
            try {
                Files.createFile(path1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!Files.exists(path2)) {
            try {
                Files.createFile(path2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader bufferedReader =
                    Files.newBufferedReader(Paths.get("D:\\AndroidProject\\MyTab\\app\\src\\3814673.lrc"), StandardCharsets.UTF_8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter bufferedWriter =
                    Files.newBufferedWriter(Paths.get("D:\\AndroidProject\\MyTab\\app", ".\\test"), StandardCharsets.UTF_8);
            String line = "Hello Word!!!";
            bufferedWriter.write(line);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取当前目录下的所有文件，不会去遍历其子目录
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("D:\\AndroidProject\\MyTab"));
            for (Path path : stream) {
                System.out.println("File Name is :" + path.getFileName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取当前目录下的所有文件，不会去遍历其子目录
        try {
            Stream<Path> streams = Files.list(Paths.get("D:\\AndroidProject\\MyTab"));
            Iterator<Path> iterator = streams.iterator();
            while (iterator.hasNext()) {
                Path path = iterator.next();
                System.out.println("File Name is : " + path.getFileName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //遍历获取一个文件夹下的所有文件及其子目录文件
        Path rootPath = Paths.get("D:\\AndroidProject\\MyTab");
        try {
            Files.walkFileTree(rootPath, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static class MyFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("File Name is: " + file.getFileName());
            return super.visitFile(file, attrs);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void fileStream() throws IOException {
        Path path = Paths.get("D:\\AndroidProject\\MyTab\\app\\src\\song.lrc");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Path path1 = Paths.get("D:\\AndroidProject\\MyTab\\app\\src\\3814673.lrc");
        Files.copy(path1, path, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(path, System.out);
        //Files.copy(System.in,path,StandardCopyOption.REPLACE_EXISTING);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void filePropery() throws IOException {

        Path zip = Paths.get("D:\\AndroidProject\\MyTab\\app\\src\\3814673.lrc");
        System.out.println("\n -----------------------------------------------");
        System.out.println(Files.getLastModifiedTime(zip));
        System.out.println(Files.size(zip));
        System.out.println(Files.isSymbolicLink(zip));
        System.out.println(Files.isDirectory(zip));
        System.out.println(Files.readAttributes(zip, "*"));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void filePermission() throws IOException {
        Path profile = Paths.get("D:\\AndroidProject\\MyTab\\app\\src\\3814673.lrc");
        PosixFileAttributes attrs = Files.readAttributes(profile, PosixFileAttributes.class);// 读取文件的权限
        Set<PosixFilePermission> posixPermissions = attrs.permissions();
        posixPermissions.clear();
        String owner = attrs.owner().getName();
        String perms = PosixFilePermissions.toString(posixPermissions);
        System.out.format("%s %s%n", owner, perms);

        posixPermissions.add(PosixFilePermission.OWNER_READ);
        posixPermissions.add(PosixFilePermission.GROUP_READ);
        posixPermissions.add(PosixFilePermission.OTHERS_READ);
        posixPermissions.add(PosixFilePermission.OWNER_WRITE);

        Files.setPosixFilePermissions(profile, posixPermissions);    // 设置文件的权限

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void asyncFileChannel() throws IOException {
        Path path = Paths.get("D:\\AndroidProject\\MyTab\\app\\src\\3814673.lrc");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048 * 10);
        Future<Integer> operation = asynchronousFileChannel.read(byteBuffer, 0);
        while (!operation.isDone()) ;
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        byteBuffer.clear();

        asynchronousFileChannel.close();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        final AsynchronousFileChannel asynchronousFileChannel1=AsynchronousFileChannel.open(path,StandardOpenOption.READ);
        asynchronousFileChannel1.read(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] bytes = new byte[attachment.remaining()];
                attachment.get(bytes);
                System.out.println(new String(bytes));
                attachment.clear();
                try {
                    asynchronousFileChannel1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

    }


}



