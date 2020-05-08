package com.sean.server;

import com.google.protobuf.Message;
import com.sean.packer.proto.Packer;
import com.sean.utils.TextUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageManager extends ChannelInboundHandlerAdapter {

    public enum MsgType {
        FILE, XML, JSON,
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * hall ddz mj
     * version
     * <p>
     * http://cdn.didabisai.com/upd/games/ddz2/src/GameEntity.lua
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object data) throws Exception {
        ByteBuf msg = (ByteBuf) data;

        List<Object> params = new ArrayList<>();
        MsgType[] mts = MsgType.values();

        while (msg.readableBytes() > 0) {
            int msgType = msg.readInt();
            int length = msg.readInt();
            byte[] d = new byte[length];
            msg.readBytes(d, 0, length);

            if (msgType < mts.length) {
                MsgType mt = mts[msgType];
                switch (mt) {
                    case FILE: {
                        String fpath = "～/Downloads/lepai/tmp/d.rar";

                        handlerFile(fpath, d);
                        params.add(new File(fpath));
                        break;
                    }
                    case XML: {
                        break;
                    }
                    case JSON: {
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } else {
                Message m = ProtoManager.getInstance().getParser(msgType).parseFrom(d);
                params.add(m);
            }
        }
        ReferenceCountUtil.release(msg);

        handler(params.toArray());
    }

    protected void handler(Object... args) {

    }

    protected void handler(File f, Packer.Message msg) {

    }

    private void handlerFile(String fpath, byte[] data) {
        FileOutputStream fos = null;
        try {
            try {
                fos = new FileOutputStream(new File(fpath), false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void extract(String zipPath, int platform, int major, int minor, int release, String hall, String... games) {
        System.out.print("---------------------------");
        String tmpPath = "C:/Users/huxf/Downloads/lepai/tmp/";
        String androidPath = "C:/Users/huxf/Downloads/lepai/0/v{release}/upd/";
        String iosPath = "C:/Users/huxf/Downloads/lepai/104/v{major}.{minor}.{release}/upd/";
        String gamePath = "C:/Users/huxf/Downloads/lepai/upd/games/";

        tmpPath = "/Users/huxf/Downloads/lepai/tmp/";
        androidPath = "/Users/huxf/Downloads/lepai/0/v{release}/upd/";
        iosPath = "/Users/huxf/Downloads/lepai/104/v{major}.{minor}.{release}/upd/";
        gamePath = "/Users/huxf/Downloads/lepai/upd/games/";

        Map<Integer, String> pathMap = new HashMap<>();
        pathMap.put(Integer.valueOf(1), androidPath);
        pathMap.put(Integer.valueOf(1 << 1), iosPath);

        Map<String, String> map = new HashMap<>();
        map.put("major", String.valueOf(major));
        map.put("minor", String.format("%02d", minor));
        map.put("release", String.valueOf(release));

//        try {
//            FileUtils.deleteDirectory(new File(tmpPath));
//            ZipFile zipFile = new ZipFile(zipPath);
//            zipFile.extractAll(tmpPath);
//
////            FileUtils.copyDirectoryToDirectory();
//        } catch (ZipException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (!TextUtils.isEmpty(hall)) {
            for (int i = 1; i <= platform; i <<= 1) {
                if (i == (i & platform)) {
                    String path = pathMap.get(Integer.valueOf(i));
                    File destDir = new File(TextUtils.format(path, map) + hall);
                    try {
                        FileUtils.deleteDirectory(destDir);
                        FileUtils.copyDirectoryToDirectory(new File(tmpPath + hall), destDir.getParentFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (null != games) {
            try {
                for (String game : games) {
                    File destDir = new File(gamePath + game);
                    FileUtils.deleteDirectory(destDir);
                    FileUtils.copyDirectoryToDirectory(new File(tmpPath+"games/"+game), destDir.getParentFile());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String zipPath = "/Users/huxf/Downloads/abc.zip";
        MessageManager.extract(zipPath, 3, 1, 0, 12, "hall", "ddz2","mj");
    }
}
