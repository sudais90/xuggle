/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.37
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.xuggle.ferry;

/**
 * Internal only. Atomic Integer represents Integers than can be updated 
 *  
 * atomically from native code.  
 * <p>  
 * This object is NOT meant to be called from Java (in fact, that'd 
 * be stupid  
 * since you'd just be calling from native code back into Java). It's 
 *  
 * here so that native code inside a JVM can have access to portable 
 *  
 * thread-safe objects.  
 * </p>  
 * <p>  
 * And that said, this method is really only Atomic if running inside 
 * a  
 * Java JVM (or other virtual machine that can provide the functionality). 
 *  
 * If running in a standalone C++ program there  
 * is no current guarantee of Atomicity.  
 * </p><p>  
 * The object just forwards to the Java object:  
 * {@link java.util.concurrent.atomic.AtomicInteger}  
 * </p>  
 */
public class AtomicInteger {
  // JNIHelper.swg: Start generated code
  // >>>>>>>>>>>>>>>>>>>>>>>>>>>

  private volatile long swigCPtr;
  protected boolean swigCMemOwn;
  @SuppressWarnings("unused")
  private JNINativeFinalizer mUnusedVariableToAllowImports;
  
  /**
   * DO NOT USE: Do not allocate this method using new.  Not part of public API.
   * <p>
   * Unfortunately this constructor is public because the internal
   * implementation needs it to be, but do not pass in values to this method
   * as you may end up crashing the virtual machine.
   * </p>
   *
   * @param cPtr A C pointer to direct memory; did we mention don't call this.
   * @param cMemoryOwn I'm not even going to tell you.  Stop it.  Go away.
   *
   */ 
  protected AtomicInteger(long cPtr, boolean cMemoryOwn) {
    swigCPtr = cPtr;
    swigCMemOwn = cMemoryOwn;
  }

  /**
   * Not part of public API.
   *
   * Get the raw value of the native object that obj is proxying for.
   *   
   * @param obj The java proxy object for a native object.
   * @return The raw pointer obj is proxying for.
   */
  public static long getCPtr(AtomicInteger obj) {
    if (obj == null) return 0;
    return obj.getMyCPtr();
  }
  
  /**
   * Not part of public API.
   *
   * Get the raw value of the native object that we're proxying for.
   *   
   * @return The raw pointer we're proxying for.
   */  
  public long getMyCPtr() {
    if (swigCPtr == 0) throw new IllegalStateException("underlying native object already deleted");
    return swigCPtr;
  }

  /**
   * Compares two values, returning true if the underlying objects in native code are the same object.
   *
   * That means you can have two different Java objects, but when you do a comparison, you'll find out
   * they are the EXACT same object.
   *
   * @return True if the underlying native object is the same.  False otherwise.
   */
  public boolean equals(Object obj) {
    boolean equal = false;
    if (obj instanceof AtomicInteger)
      equal = (((AtomicInteger)obj).swigCPtr == this.swigCPtr);
    return equal;
  }
  
  /**
   * Get a hashable value for this object.
   *
   * @return the hashable value.
   */
  public int hashCode() {
     return (int)swigCPtr;
  }
  
  /**
   * Finalize this object.  Note this should only exists on non RefCounted objects.
   */
  protected void finalize()
  {
    delete();
  }

  // <<<<<<<<<<<<<<<<<<<<<<<<<<<
  // JNIHelper.swg: End generated code

  public synchronized void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      FerryJNI.delete_AtomicInteger(swigCPtr);
    }
    swigCPtr = 0;
  }

  public AtomicInteger() {
    this(FerryJNI.new_AtomicInteger__SWIG_0(), true);
  }

  public AtomicInteger(int arg0) {
    this(FerryJNI.new_AtomicInteger__SWIG_1(arg0), true);
  }

  public int get() {
    return FerryJNI.AtomicInteger_get(swigCPtr, this);
  }

  public void set(int arg0) {
    FerryJNI.AtomicInteger_set(swigCPtr, this, arg0);
  }

  public int getAndSet(int arg0) {
    return FerryJNI.AtomicInteger_getAndSet(swigCPtr, this, arg0);
  }

  public int getAndIncrement() {
    return FerryJNI.AtomicInteger_getAndIncrement(swigCPtr, this);
  }

  public int getAndDecrement() {
    return FerryJNI.AtomicInteger_getAndDecrement(swigCPtr, this);
  }

  public int getAndAdd(int arg0) {
    return FerryJNI.AtomicInteger_getAndAdd(swigCPtr, this, arg0);
  }

  public int incrementAndGet() {
    return FerryJNI.AtomicInteger_incrementAndGet(swigCPtr, this);
  }

  public int decrementAndGet() {
    return FerryJNI.AtomicInteger_decrementAndGet(swigCPtr, this);
  }

  public int addAndGet(int arg0) {
    return FerryJNI.AtomicInteger_addAndGet(swigCPtr, this, arg0);
  }

/**
 * Compare the current value to expected, and if  
 * they are equal, set the current value to update.  
 * @param	expected the value expected  
 * @param	update the value to update to  
 * @return	true if equal  
 */
  public boolean compareAndSet(int expected, int update) {
    return FerryJNI.AtomicInteger_compareAndSet(swigCPtr, this, expected, update);
  }

/**
 * @return	true if we're actually able to guarantee  
 * atomicity; false if we can't.  
 */
  public boolean isAtomic() {
    return FerryJNI.AtomicInteger_isAtomic(swigCPtr, this);
  }

}
