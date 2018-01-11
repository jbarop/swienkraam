package swienkraam;

import java.beans.ConstructorProperties;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.System.arraycopy;

/**
 * Implementation of {@link HamburgStreetTree} with an byte array.
 */
public class HamburgStreetTreeByteImpl extends HamburgStreetTree {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final DateTimeFormatter LOCALDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final int NUM_DYNAMICS = 17;
    private static final int POS_INDEX = 21;

    private final byte[] storage;

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeByteImpl(final String gmlId,
                                     final String objectId,
                                     final int baumId,
                                     final String gattung,
                                     final String gattungLatein,
                                     final String gattungDeutsch,
                                     final String art,
                                     final String artLatein,
                                     final String artDeutsch,
                                     final int pflanzjahr,
                                     final String kronendurchmesser,
                                     final String kronendmzahl,
                                     final int stammumfang,
                                     final String stammumfangzahl,
                                     final String strasse,
                                     final String hausnummer,
                                     final short ortsteilNr,
                                     final LocalDate standBearbeitung,
                                     final String bezirk,
                                     final String srsName,
                                     final String srsDimension,
                                     final String pos) {
        final byte[] emptyBytes = new byte[0];

        final byte[] gmlIdBytes = gmlId != null ? gmlId.getBytes(CHARSET) : emptyBytes;
        final byte[] objectIdBytes = objectId != null ? objectId.getBytes(CHARSET) : emptyBytes;
        final byte[] gattungBytes = gattung != null ? gattung.getBytes(CHARSET) : emptyBytes;
        final byte[] gattungLateinBytes = gattungLatein != null ? gattungLatein.getBytes(CHARSET) : emptyBytes;
        final byte[] gattungDeutschBytes = gattungDeutsch != null ? gattungDeutsch.getBytes(CHARSET) : emptyBytes;
        final byte[] artBytes = art != null ? art.getBytes(CHARSET) : emptyBytes;
        final byte[] artLateinBytes = artLatein != null ? artLatein.getBytes(CHARSET) : emptyBytes;
        final byte[] artDeutschBytes = artDeutsch != null ? artDeutsch.getBytes(CHARSET) : emptyBytes;
        final byte[] kronendurchmesserBytes = kronendurchmesser != null ? kronendurchmesser.getBytes(CHARSET) : emptyBytes;
        final byte[] kronendmzahlBytes = kronendmzahl != null ? kronendmzahl.getBytes(CHARSET) : emptyBytes;
        final byte[] stammumfangzahlBytes = stammumfangzahl != null ? stammumfangzahl.getBytes(CHARSET) : emptyBytes;
        final byte[] strasseBytes = strasse != null ? strasse.getBytes(CHARSET) : emptyBytes;
        final byte[] hausnummerBytes = hausnummer != null ? hausnummer.getBytes(CHARSET) : emptyBytes;
        final byte[] bezirkBytes = bezirk != null ? bezirk.getBytes(CHARSET) : emptyBytes;
        final byte[] srsNamekBytes = srsName != null ? srsName.getBytes(CHARSET) : emptyBytes;
        final byte[] srsDimensionBytes = srsDimension != null ? srsDimension.getBytes(CHARSET) : emptyBytes;
        final byte[] posBytes = pos != null ? pos.getBytes(CHARSET) : emptyBytes;

        final byte[] storage = new byte[
                3 // nullable bitfield
                        + 4 // int baumId
                        + 4 // int pflanzjahr
                        + 4 // int stammumfang
                        + 2 // short ortsteilNr
                        + 4 // int standBearbeitung
                        + NUM_DYNAMICS * 2 // dynamic index
                        + gmlIdBytes.length // String gmlId
                        + objectIdBytes.length // String objectId
                        + gattungBytes.length // String gattung
                        + gattungLateinBytes.length // String gattungLatein
                        + gattungDeutschBytes.length // String gattungDeutsch
                        + artBytes.length // String artLatein
                        + artLateinBytes.length // String artLatein
                        + artDeutschBytes.length // String artDeutsch
                        + kronendurchmesserBytes.length // String kronendurchmesser
                        + kronendmzahlBytes.length // String kronendmzahl
                        + stammumfangzahlBytes.length // String stammumfangzahl
                        + strasseBytes.length // String strasse
                        + hausnummerBytes.length // String hausnummer
                        + bezirkBytes.length // String bezirk)
                        + srsNamekBytes.length // String srsName
                        + srsDimensionBytes.length // String srsDimension
                        + posBytes.length // String pos
                ];

        storeInt(storage, 3, baumId);
        storeInt(storage, 7, pflanzjahr);
        storeInt(storage, 11, stammumfang);
        storeShort(storage, 15, ortsteilNr);
        storeLocalDate(storage, 0, (byte) 0x01, 17, standBearbeitung);

        storeBytes(storage, 0, (byte) 0x02, 0, gmlIdBytes, gmlId);
        storeBytes(storage, 0, (byte) 0x04, 1, objectIdBytes, objectId);
        storeBytes(storage, 0, (byte) 0x08, 2, gattungBytes, gattung);
        storeBytes(storage, 0, (byte) 0x10, 3, gattungLateinBytes, gattungLatein);
        storeBytes(storage, 0, (byte) 0x20, 4, gattungDeutschBytes, gattungDeutsch);
        storeBytes(storage, 0, (byte) 0x40, 5, artBytes, art);
        storeBytes(storage, 0, (byte) 0x80, 6, artLateinBytes, artLatein);
        storeBytes(storage, 1, (byte) 0x01, 7, artDeutschBytes, artDeutsch);
        storeBytes(storage, 1, (byte) 0x02, 8, kronendurchmesserBytes, kronendurchmesser);
        storeBytes(storage, 1, (byte) 0x04, 9, kronendmzahlBytes, kronendmzahl);
        storeBytes(storage, 1, (byte) 0x08, 10, stammumfangzahlBytes, stammumfangzahl);
        storeBytes(storage, 1, (byte) 0x10, 11, strasseBytes, strasse);
        storeBytes(storage, 1, (byte) 0x20, 12, hausnummerBytes, hausnummer);
        storeBytes(storage, 1, (byte) 0x40, 13, bezirkBytes, bezirk);
        storeBytes(storage, 1, (byte) 0x80, 14, srsNamekBytes, srsName);
        storeBytes(storage, 2, (byte) 0x01, 15, srsDimensionBytes, srsDimension);
        storeBytes(storage, 2, (byte) 0x02, 16, posBytes, pos);

        this.storage = preSave(storage);
    }

    @Override
    public String getGmlId() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x02, POS_INDEX + NUM_DYNAMICS * 2, loadIndex(storage, 0));
    }

    @Override
    public String getObjectId() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x04, loadIndex(storage, 0), loadIndex(storage, 1));
    }

    @Override
    public int getBaumId() {
        return loadInt(retrieve(), 3);
    }

    @Override
    public String getGattung() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x08, loadIndex(storage, 1), loadIndex(storage, 2));
    }

    @Override
    public String getGattungLatein() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x10, loadIndex(storage, 2), loadIndex(storage, 3));
    }

    @Override
    public String getGattungDeutsch() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x20, loadIndex(storage, 3), loadIndex(storage, 4));
    }

    @Override
    public String getArt() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x40, loadIndex(storage, 4), loadIndex(storage, 5));
    }

    @Override
    public String getArtLatein() {
        byte[] storage = retrieve();
        return loadString(storage, 0, (byte) 0x80, loadIndex(storage, 5), loadIndex(storage, 6));
    }

    @Override
    public String getArtDeutsch() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x01, loadIndex(storage, 6), loadIndex(storage, 7));
    }

    @Override
    public int getPflanzjahr() {
        return loadInt(retrieve(), 7);
    }

    @Override
    public String getKronendurchmesser() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x02, loadIndex(storage, 7), loadIndex(storage, 8));
    }

    @Override
    public String getKronendmzahl() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x04, loadIndex(storage, 8), loadIndex(storage, 9));
    }

    @Override
    public int getStammumfang() {
        return loadInt(retrieve(), 11);
    }

    @Override
    public String getStammumfangzahl() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x08, loadIndex(storage, 9), loadIndex(storage, 10));
    }

    @Override
    public String getStrasse() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x10, loadIndex(storage, 10), loadIndex(storage, 11));
    }

    @Override
    public String getHausnummer() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x20, loadIndex(storage, 11), loadIndex(storage, 12));
    }

    @Override
    public short getOrtsteilNr() {
        return loadShort(retrieve(), 15);
    }

    @Override
    public LocalDate getStandBearbeitung() {
        return loadLocalDate(retrieve(), 0, (byte) 0x01, 17);
    }

    @Override
    public String getBezirk() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x40, loadIndex(storage, 12), loadIndex(storage, 13));
    }

    @Override
    public String getSrsName() {
        byte[] storage = retrieve();
        return loadString(storage, 1, (byte) 0x80, loadIndex(storage, 13), loadIndex(storage, 14));
    }

    @Override
    public String getSrsDimension() {
        byte[] storage = retrieve();
        return loadString(storage, 2, (byte) 0x01, loadIndex(storage, 14), loadIndex(storage, 15));
    }

    @Override
    public String getPos() {
        byte[] storage = retrieve();
        return loadString(storage, 2, (byte) 0x02, loadIndex(storage, 15), loadIndex(storage, 16));
    }

    protected byte[] preSave(final byte[] storage) {
        return storage;
    }

    protected byte[] retrieve() {
        return storage;
    }

    private static void storeInt(final byte[] storage, final int position, final int value) {
        storage[position] = (byte) (value >>> 24);
        storage[position + 1] = (byte) (value >>> 16);
        storage[position + 2] = (byte) (value >>> 8);
        storage[position + 3] = (byte) value;
    }

    private static void storeShort(final byte[] storage, final int position, final short value) {
        storage[position] = (byte) (value >>> 8);
        storage[position + 1] = (byte) value;
    }

    private static void storeLocalDate(final byte[] storage, final int bitmaskIndex, final byte bitmaskValue,
                                       final int position, final LocalDate value) {
        if (value != null) {
            storage[bitmaskIndex] |= bitmaskValue;
            // Instead of storing the timestamp as long (8 bytes) the date is stored as int ("yyymmdd", 4 bytes)
            storeInt(storage, position, Integer.valueOf(value.format(LOCALDATE_FORMATTER)));
        }
    }

    private static void storeBytes(final byte[] storage, final int bitmaskIndex, final byte bitmaskValue,
                                   final int dynamicIndex, final byte[] data, final String originalObject) {
        final short storageOffset;
        if (dynamicIndex == 0) {
            storageOffset = POS_INDEX + NUM_DYNAMICS * 2;
        } else {
            storageOffset = loadIndex(storage, dynamicIndex - 1);
        }

        if (originalObject != null) {
            storage[bitmaskIndex] |= bitmaskValue;
            arraycopy(data, 0, storage, storageOffset, data.length);
        }
        storeShort(storage, dynamicIndex * 2 + POS_INDEX, (short) (storageOffset + data.length));
    }

    private static ByteBuffer seek(byte[] storage, int position) {
        return (ByteBuffer) ByteBuffer.wrap(storage, 0, storage.length).position(position);
    }

    private static int loadInt(final byte[] storage, final int position) {
        return seek(storage, position).getInt();
    }

    private static short loadShort(final byte[] storage, final int position) {
        return seek(storage, position).getShort();
    }

    private static LocalDate loadLocalDate(final byte[] storage, final int bitmaskIndex, final byte bitmaskValue,
                                           final int position) {
        if ((storage[bitmaskIndex] & bitmaskValue) == bitmaskValue) {
            return LocalDate.parse(String.valueOf(loadInt(storage, position)), LOCALDATE_FORMATTER);
        } else {
            return null;
        }
    }

    private static short loadIndex(final byte[] storage, final int position) {
        return loadShort(storage, POS_INDEX + position * 2);
    }

    private static byte[] loadBytes(final byte[] storage, final int startPosition, final int endPosition) {
        final int length = endPosition - startPosition;
        final byte[] array = new byte[length];
        seek(storage, startPosition).get(array, 0, length);
        return array;
    }

    private static String loadString(final byte[] storage, final int bitmaskIndex, final byte bitmaskValue,
                                     final int startPosition, final int endPosition) {
        if ((storage[bitmaskIndex] & bitmaskValue) == bitmaskValue) {
            return new String(loadBytes(storage, startPosition, endPosition), CHARSET);
        } else {
            return null;
        }
    }

}
