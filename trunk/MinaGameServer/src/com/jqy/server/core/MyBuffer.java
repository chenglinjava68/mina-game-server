package com.jqy.server.core;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 针对MINA的IoBuffer进行封装
 * 
 * @author Simple
 * @date 2013-6-26 下午04:06:12
 * @Description TODO
 */
public class MyBuffer {

  // getPrefixedString()
  private static final int STRING_LENTH=2;

  protected Charset charset_utf16be=Charset.forName("UTF-16BE");

  protected CharsetEncoder charsetEncoder_utf16be=charset_utf16be.newEncoder();

  protected CharsetDecoder charsetDecoder_utf16be=charset_utf16be.newDecoder();

  // getString()
  protected Charset charset_utf8=Charset.forName("UTF-8");

  protected CharsetEncoder charsetEncoder_utf8=charset_utf8.newEncoder();

  protected CharsetDecoder charsetDecoder_utf8=charset_utf8.newDecoder();

  // MINA IoBuffer
  private IoBuffer ioBuffer;

  public MyBuffer() {
  }

  public MyBuffer(IoBuffer ioBuffer) {
    this.setIoBuffer(ioBuffer);
  }

  public static MyBuffer allocate(int size) {
    MyBuffer myBuffer=new MyBuffer();
    myBuffer.setIoBuffer(IoBuffer.allocate(size).setAutoExpand(true));
    return myBuffer;
  }

  public String getPrefixedString() {
    try {
      return getIoBuffer().getPrefixedString(STRING_LENTH, charsetDecoder_utf16be);
    } catch(CharacterCodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public MyBuffer putPrefixedString(String s) {
    if(s == null)
      return this;
    try {
      getIoBuffer().putPrefixedString(s, STRING_LENTH, charsetEncoder_utf16be);
    } catch(CharacterCodingException e) {
      e.printStackTrace();
    }
    return this;
  }

  /*
   * public String getString() { try { return getIoBuffer().getString(getIoBuffer().getShort(), charsetDecoder_utf8); }
   * catch(CharacterCodingException e) { e.printStackTrace(); } return null; }
   */
  /*
   * public MyBuffer putString(String s) { if(s == null) return this; try { getIoBuffer().putShort((short)s.getBytes().length);
   * getIoBuffer().putString(s, charsetEncoder_utf8); } catch(CharacterCodingException e) { e.printStackTrace(); } return this; }
   */
  public byte get() {
    return getIoBuffer().get();
  }

  public IoBuffer get(byte[] b) {
    return getIoBuffer().get(b);
  }

  public MyBuffer put(byte b) {
    getIoBuffer().put(b);
    return this;
  }

  public void put(IoBuffer buf) {
    getIoBuffer().put(buf);
  }

  public MyBuffer put(byte[] b) {
    getIoBuffer().put(b);
    return this;
  }

  public short getShort() {
    return getIoBuffer().getShort();
  }

  public MyBuffer putShort(short s) {
    getIoBuffer().putShort(s);
    return this;
  }

  public int getInt() {
    return getIoBuffer().getInt();
  }

  public MyBuffer putInt(int i) {
    getIoBuffer().putInt(i);
    return this;
  }

  public long getLong() {
    return getIoBuffer().getLong();
  }

  public MyBuffer putLong(long l) {
    getIoBuffer().putLong(l);
    return this;
  }

  public float getFloat() {
    return getIoBuffer().getFloat();
  }

  public MyBuffer putInt(float f) {
    getIoBuffer().putFloat(f);
    return this;
  }

  public double getDouble() {
    return getIoBuffer().getDouble();
  }

  public MyBuffer putDouble(double d) {
    getIoBuffer().putDouble(d);
    return this;
  }

  public MyBuffer flip() {
    getIoBuffer().flip();
    return this;
  }

  public int remaining() {
    return getIoBuffer().remaining();
  }

  public MyBuffer mark() {
    getIoBuffer().mark();
    return this;
  }

  public MyBuffer reset() {
    getIoBuffer().reset();
    return this;
  }

  public void setIoBuffer(IoBuffer ioBuffer) {
    this.ioBuffer=ioBuffer;
  }

  public IoBuffer getIoBuffer() {
    return ioBuffer;
  }

  public int limit() {
    return getIoBuffer().limit();
  }

  public boolean hasRemaining() {
    return this.ioBuffer.hasRemaining();
  }
}
