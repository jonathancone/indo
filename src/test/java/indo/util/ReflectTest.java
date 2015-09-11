/*
 * Copyright 2015  Jonathan Cone
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indo.util;

import org.junit.Test;

import java.time.LocalTime;

import static indo.util.Reflect.on;
import static org.junit.Assert.*;

/**
 * Created by jcone on 8/27/15.
 */
public class ReflectTest {
    @Test
    public void testInvoke() throws Exception {
        Item item = new Item();

        item.setObjTest("String");

        assertEquals("String", on(item).invoke("getObjTest").lastReturn().get());
    }

    @Test
    public void testSetPropertyObj() {

        Item item = new Item();

        on(item).property("objTest", "test");

        assertEquals("test", item.getObjTest());
    }

    @Test
    public void testSetPropertyObjArray() {

        Item item = new Item();

        on(item).property("objArrayTest", new String[]{"this", "is", "a", "test"});

        assertArrayEquals(new String[]{"this", "is", "a", "test"}, item.getObjArrayTest());
    }

    @Test
    public void testSetPropertyBoolean() {

        Item item = new Item();

        on(item).property("booleanTest", true);

        assertTrue(item.isBooleanTest());
    }

    @Test
    public void testSetPropertyBooleanArray() {

        Item item = new Item();

        on(item).property("booleanArrayTest", new boolean[]{true, true, true});

        assertArrayEquals(new boolean[]{true, true, true}, item.getBooleanArrayTest());
    }

    @Test
    public void testSetPropertyChar() {

        Item item = new Item();

        on(item).property("charTest", 'C');

        assertEquals('C', item.getCharTest());
    }

    @Test
    public void testSetPropertyCharArray() {

        Item item = new Item();

        on(item).property("charArrayTest", new char[]{'D', 'E', 'F'});

        assertArrayEquals(new char[]{'D', 'E', 'F'}, item.getCharArrayTest());
    }

    @Test
    public void testSetPropertyByte() {

        Item item = new Item();

        on(item).property("byteTest", (byte) 07);

        assertEquals((byte) 07, item.getByteTest());
    }

    @Test
    public void testSetPropertyByteArray() {

        Item item = new Item();

        on(item).property("byteArrayTest", new byte[]{02, 03, 05});

        assertArrayEquals(new byte[]{02, 03, 05}, item.getByteArrayTest());
    }

    @Test
    public void testSetPropertyShort() {

        Item item = new Item();

        on(item).property("shortTest", (short) 64);

        assertEquals((short) 64, item.getShortTest());
    }

    @Test
    public void testSetPropertyShortArray() {

        Item item = new Item();

        on(item).property("shortArrayTest", new short[]{16, 32, 48});

        assertArrayEquals(new short[]{16, 32, 48}, item.getShortArrayTest());
    }

    @Test
    public void testSetPropertyInt() {

        Item item = new Item();

        on(item).property("intTest", 64);

        assertEquals(64, item.getIntTest());
    }

    @Test
    public void testSetPropertyIntArray() {

        Item item = new Item();

        on(item).property("intArrayTest", new int[]{16, 32, 48});

        assertArrayEquals(new int[]{16, 32, 48}, item.getIntArrayTest());
    }

    @Test
    public void testSetPropertyLong() {

        Item item = new Item();

        on(item).property("longTest", (long) 64);

        assertEquals((long) 64, item.getLongTest());
    }

    @Test
    public void testSetPropertyLongArray() {

        Item item = new Item();

        on(item).property("longArrayTest", new long[]{16, 32, 48});

        assertArrayEquals(new long[]{16, 32, 48}, item.getLongArrayTest());
    }

    @Test
    public void testSetPropertyFloat() {

        Item item = new Item();

        on(item).property("floatTest", (float) 64);

        assertEquals((float) 64, item.getFloatTest(), .001);
    }

    @Test
    public void testSetPropertyFloatArray() {

        Item item = new Item();

        on(item).property("floatArrayTest", new float[]{16, 32, 48});

        assertArrayEquals(new float[]{16, 32, 48}, item.getFloatArrayTest(), (float) .001);
    }

    @Test
    public void testSetPropertyDouble() {

        Item item = new Item();

        on(item).property("doubleTest", (double) 64);

        assertEquals((double) 64, item.getDoubleTest(), .001);
    }

    @Test
    public void testSetPropertyDoubleArray() {

        Item item = new Item();

        on(item).property("doubleArrayTest", new double[]{16, 32, 48});

        assertArrayEquals(new double[]{16, 32, 48}, item.getDoubleArrayTest(), .001);
    }

    @Test
    public void testNewInstance() throws Exception {
        assertNotNull(on(Item.class).newInstance().getInstance());
    }


    @Test
    public void testNewInstanceSetters() throws Exception {

        LocalTime lt = LocalTime.now();

        Item instance = on(Item.class)
                .newInstance()
                .property("objTest", lt)
                .property("booleanTest", true)
                .property("intTest", 400)
                .getInstance();

        assertNotNull(instance);
        assertEquals(lt, instance.getObjTest());
        assertTrue(instance.isBooleanTest());
        assertEquals(400, instance.getIntTest());

    }

    @Test
    public void testGetProperty() throws Exception {

        LocalTime lt = LocalTime.now();

        Reflect<Item> reflect = on(Item.class)
                .newInstance()
                .property("objTest", lt)
                .property("booleanTest", true)
                .property("intTest", 400);

        assertEquals(0, reflect.returnCount());

        assertEquals(lt, reflect.property("objTest").get());
        assertEquals(true, reflect.property("booleanTest").get());
        assertEquals(400, (int) reflect.property("intTest").get());
    }

    @Test
    public void testToType1() throws Exception {
        assertArrayEquals(new Class<?>[]{String.class, Integer.class, Double.class},
                Reflect.toType("String", 100, 4.5));
    }

    @Test
    public void testToType2() throws Exception {
        assertArrayEquals(new Class<?>[]{String.class},
                Reflect.toType("String"));
    }

    @Test
    public void testToType3() throws Exception {
        assertArrayEquals(new Class<?>[]{}, Reflect.toType());
    }

    @Test
    public void testProperty1() throws Exception {
        assertFalse(on(new Item()).<Boolean>property("booleanTest").get());
    }

    protected static class Item {
        private boolean booleanTest;
        private boolean[] booleanArrayTest;

        private char charTest;
        private char[] charArrayTest;

        private byte byteTest;
        private byte[] byteArrayTest;

        private short shortTest;
        private short[] shortArrayTest;

        private int intTest;
        private int[] intArrayTest;

        private long longTest;
        private long[] longArrayTest;

        private float floatTest;
        private float[] floatArrayTest;

        private double doubleTest;
        private double[] doubleArrayTest;

        private Object objTest;
        private Object[] objArrayTest;

        public boolean isBooleanTest() {
            return booleanTest;
        }

        public void setBooleanTest(boolean booleanTest) {
            this.booleanTest = booleanTest;
        }

        public boolean[] getBooleanArrayTest() {
            return booleanArrayTest;
        }

        public void setBooleanArrayTest(boolean[] booleanArrayTest) {
            this.booleanArrayTest = booleanArrayTest;
        }

        public char getCharTest() {
            return charTest;
        }

        public void setCharTest(char charTest) {
            this.charTest = charTest;
        }

        public char[] getCharArrayTest() {
            return charArrayTest;
        }

        public void setCharArrayTest(char[] charArrayTest) {
            this.charArrayTest = charArrayTest;
        }

        public byte getByteTest() {
            return byteTest;
        }

        public void setByteTest(byte byteTest) {
            this.byteTest = byteTest;
        }

        public byte[] getByteArrayTest() {
            return byteArrayTest;
        }

        public void setByteArrayTest(byte[] byteArrayTest) {
            this.byteArrayTest = byteArrayTest;
        }

        public short getShortTest() {
            return shortTest;
        }

        public void setShortTest(short shortTest) {
            this.shortTest = shortTest;
        }

        public short[] getShortArrayTest() {
            return shortArrayTest;
        }

        public void setShortArrayTest(short[] shortArrayTest) {
            this.shortArrayTest = shortArrayTest;
        }

        public int getIntTest() {
            return intTest;
        }

        public void setIntTest(int intTest) {
            this.intTest = intTest;
        }

        public int[] getIntArrayTest() {
            return intArrayTest;
        }

        public void setIntArrayTest(int[] intArrayTest) {
            this.intArrayTest = intArrayTest;
        }

        public long getLongTest() {
            return longTest;
        }

        public void setLongTest(long longTest) {
            this.longTest = longTest;
        }

        public long[] getLongArrayTest() {
            return longArrayTest;
        }

        public void setLongArrayTest(long[] longArrayTest) {
            this.longArrayTest = longArrayTest;
        }

        public float getFloatTest() {
            return floatTest;
        }

        public void setFloatTest(float floatTest) {
            this.floatTest = floatTest;
        }

        public float[] getFloatArrayTest() {
            return floatArrayTest;
        }

        public void setFloatArrayTest(float[] floatArrayTest) {
            this.floatArrayTest = floatArrayTest;
        }

        public double getDoubleTest() {
            return doubleTest;
        }

        public void setDoubleTest(double doubleTest) {
            this.doubleTest = doubleTest;
        }

        public double[] getDoubleArrayTest() {
            return doubleArrayTest;
        }

        public void setDoubleArrayTest(double[] doubleArrayTest) {
            this.doubleArrayTest = doubleArrayTest;
        }

        public Object getObjTest() {
            return objTest;
        }

        public void setObjTest(Object objTest) {
            this.objTest = objTest;
        }

        public Object[] getObjArrayTest() {
            return objArrayTest;
        }

        public void setObjArrayTest(Object[] objArrayTest) {
            this.objArrayTest = objArrayTest;
        }
    }
}