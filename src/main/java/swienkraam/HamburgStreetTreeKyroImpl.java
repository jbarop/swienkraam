package swienkraam;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

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
public class HamburgStreetTreeKyroImpl extends HamburgStreetTree {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final DateTimeFormatter LOCALDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final int NUM_DYNAMICS = 17;
    private static final int POS_INDEX = 18;

    private final byte[] storage;

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeKyroImpl(final String gmlId,
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

        Kryo kryo = new Kryo();
        Output output = new Output(1024, 100_000_000);
        kryo.writeObject(output , new HamburgStreetTreeObjectImpl(gmlId, objectId, baumId, gattung, gattungLatein, gattungDeutsch, art, artLatein, artDeutsch, pflanzjahr, kronendurchmesser, kronendmzahl, stammumfang, stammumfangzahl, strasse, hausnummer, ortsteilNr, standBearbeitung, bezirk, srsName, srsDimension, pos));


        this.storage = output.getBuffer();
    }

    private HamburgStreetTreeObjectImpl wrap() {
        return new Kryo().readObject(new Input(storage), HamburgStreetTreeObjectImpl.class);
    }

    @Override
    public String getGmlId() {
        return wrap().getGmlId();
    }

    @Override
    public String getObjectId() {
        return wrap().getObjectId();
    }

    @Override
    public int getBaumId() {
        return wrap().getBaumId();
    }

    @Override
    public String getGattung() {
        return wrap().getGattung();
    }

    @Override
    public String getGattungLatein() {
        return wrap().getGattungLatein();
    }

    @Override
    public String getGattungDeutsch() {
        return wrap().getGattungDeutsch();
    }

    @Override
    public String getArt() {
        return wrap().getArt();
    }

    @Override
    public String getArtLatein() {
        return wrap().getArtLatein();
    }

    @Override
    public String getArtDeutsch() {
        return wrap().getArtDeutsch();
    }

    @Override
    public int getPflanzjahr() {
        return wrap().getPflanzjahr();
    }

    @Override
    public String getKronendurchmesser() {
        return wrap().getKronendurchmesser();
    }

    @Override
    public String getKronendmzahl() {
        return wrap().getKronendmzahl();
    }

    @Override
    public int getStammumfang() {
        return wrap().getStammumfang();
    }

    @Override
    public String getStammumfangzahl() {
        return wrap().getStammumfangzahl();
    }

    @Override
    public String getStrasse() {
        return wrap().getStrasse();
    }

    @Override
    public String getHausnummer() {
        return wrap().getHausnummer();
    }

    @Override
    public short getOrtsteilNr() {
        return wrap().getOrtsteilNr();
    }

    @Override
    public LocalDate getStandBearbeitung() {
        return wrap().getStandBearbeitung();
    }

    @Override
    public String getBezirk() {
        return wrap().getBezirk();
    }

    @Override
    public String getSrsName() {
        return wrap().getSrsName();
    }

    @Override
    public String getSrsDimension() {
        return wrap().getSrsDimension();
    }

    @Override
    public String getPos() {
        return wrap().getPos();
    }

}
