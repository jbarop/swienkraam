/* Generated SBE (Simple Binary Encoding) message codec */
package swienkraam.generated.sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.sbe.*;

@javax.annotation.Generated(value = { "uk.co.real_logic.sbe.generation.java.JavaGenerator" })
@SuppressWarnings("all")
public class VarStringEncodingEncoder implements CompositeEncoderFlyweight
{
    public static final int ENCODED_LENGTH = -1;
    private int offset;
    private MutableDirectBuffer buffer;

    public VarStringEncodingEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        this.buffer = buffer;
        this.offset = offset;

        return this;
    }

    public MutableDirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public int encodedLength()
    {
        return ENCODED_LENGTH;
    }

    public static int lengthEncodingOffset()
    {
        return 0;
    }

    public static int lengthEncodingLength()
    {
        return 2;
    }

    public static int lengthNullValue()
    {
        return 65535;
    }

    public static int lengthMinValue()
    {
        return 0;
    }

    public static int lengthMaxValue()
    {
        return 254;
    }

    public VarStringEncodingEncoder length(final int value)
    {
        buffer.putShort(offset + 0, (short)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int varDataEncodingOffset()
    {
        return 2;
    }

    public static int varDataEncodingLength()
    {
        return -1;
    }

    public static short varDataNullValue()
    {
        return (short)255;
    }

    public static short varDataMinValue()
    {
        return (short)0;
    }

    public static short varDataMaxValue()
    {
        return (short)254;
    }

    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        VarStringEncodingDecoder writer = new VarStringEncodingDecoder();
        writer.wrap(buffer, offset);

        return writer.appendTo(builder);
    }
}
