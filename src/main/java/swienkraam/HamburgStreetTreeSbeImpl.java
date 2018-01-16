package swienkraam;

import org.agrona.concurrent.UnsafeBuffer;
import swienkraam.generated.sbe.HamburgSbeTreeDecoder;
import swienkraam.generated.sbe.HamburgSbeTreeEncoder;

import java.beans.ConstructorProperties;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HamburgStreetTreeSbeImpl extends HamburgStreetTree {

    private static final DateTimeFormatter LOCALDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final byte[] storage;

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeSbeImpl(final String gmlId,
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
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        final UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
        HamburgSbeTreeEncoder encoder = new HamburgSbeTreeEncoder();
        encoder.wrap(directBuffer, 0)
                .gmlId(gmlId)
                .objectId(objectId)
                .baumid(baumId)
                .gattung(gattung)
                .gattungLatein(gattungLatein)
                .gattungDeutsch(gattungDeutsch)
                .art(art)
                .artLatein(artLatein)
                .artDeutsch(artDeutsch)
                .pflanzjahr(pflanzjahr)
                .kronendurchmesser(kronendurchmesser)
                .kronendmzahl(kronendmzahl)
                .stammumfang(stammumfang)
                .stammumfangzahl(stammumfangzahl)
                .strasse(strasse)
                .hausnummer(hausnummer)
                .ortsteilNr(ortsteilNr)
                // Instead of storing the timestamp as long (8 bytes) the date is stored as int ("yyymmdd", 4 bytes)
                .standBearbeitung(Integer.valueOf(standBearbeitung.format(LOCALDATE_FORMATTER)))
                .bezirk(bezirk)
                .srsName(srsName)
                .srsDimension(srsDimension)
                .pos(pos);

        int length = encoder.encodedLength();
        this.storage = new byte[length];
        System.arraycopy(byteBuffer.array(), 0, this.storage, 0, length);
    }

    @Override
    String getGmlId() {
        return decode().getGmlId();
    }

    @Override
    String getObjectId() {
        return decode().getObjectId();
    }

    @Override
    int getBaumId() {
        return decode().getBaumId();
    }

    @Override
    String getGattung() {
        return decode().getGattung();
    }

    @Override
    String getGattungLatein() {
        return decode().getGattungLatein();
    }

    @Override
    String getGattungDeutsch() {
        return decode().getGattungDeutsch();
    }

    @Override
    String getArt() {
        return decode().getArt();
    }

    @Override
    String getArtLatein() {
        return decode().getArtLatein();
    }

    @Override
    String getArtDeutsch() {
        return decode().getArtDeutsch();
    }

    @Override
    int getPflanzjahr() {
        return decode().getPflanzjahr();
    }

    @Override
    String getKronendurchmesser() {
        return decode().getKronendurchmesser();
    }

    @Override
    String getKronendmzahl() {
        return decode().getKronendmzahl();
    }

    @Override
    int getStammumfang() {
        return decode().getStammumfang();
    }

    @Override
    String getStammumfangzahl() {
        return decode().getStammumfangzahl();
    }

    @Override
    String getStrasse() {
        return decode().getStrasse();
    }

    @Override
    String getHausnummer() {
        return decode().getHausnummer();
    }

    @Override
    short getOrtsteilNr() {
        return decode().getOrtsteilNr();
    }

    @Override
    LocalDate getStandBearbeitung() {
        return decode().getStandBearbeitung();
    }

    @Override
    String getBezirk() {
        return decode().getBezirk();
    }

    @Override
    String getSrsName() {
        return decode().getSrsName();
    }

    @Override
    String getSrsDimension() {
        return decode().getSrsDimension();
    }

    @Override
    String getPos() {
        return decode().getPos();
    }

    private HamburgStreetTree decode() {
        final UnsafeBuffer directBuffer = new UnsafeBuffer(ByteBuffer.wrap(storage));
        final HamburgSbeTreeDecoder decoder = new HamburgSbeTreeDecoder();
        decoder.wrap(directBuffer, 0, HamburgSbeTreeDecoder.BLOCK_LENGTH, HamburgSbeTreeDecoder.SCHEMA_VERSION);

        return new HamburgStreetTreeObjectImpl(decoder.gmlId(), decoder.objectId(), (int) decoder.baumid(),
                decoder.gattung(), decoder.gattungLatein(), decoder.gattungDeutsch(), decoder.art(),
                decoder.artLatein(), decoder.artDeutsch(), decoder.pflanzjahr(), decoder.kronendurchmesser(),
                decoder.kronendmzahl(), (int) decoder.stammumfang(), decoder.stammumfangzahl(), decoder.strasse(),
                decoder.hausnummer(), (short) decoder.ortsteilNr(),
                LocalDate.parse(String.valueOf(decoder.standBearbeitung()), LOCALDATE_FORMATTER), decoder.bezirk(), decoder.srsName(),
                decoder.srsDimension(), decoder.pos());
    }

}
