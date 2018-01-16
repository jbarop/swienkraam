package swienkraam;

import com.google.protobuf.InvalidProtocolBufferException;
import swienkraam.generated.protobuf.HamburgStreetTreeProtos;

import java.beans.ConstructorProperties;
import java.time.*;

/**
 * Implementation of {@link HamburgStreetTree} with an byte array.
 */
public class HamburgStreetTreeProtobufImpl extends HamburgStreetTree {

    private final byte[] storage;

    @ConstructorProperties({
            "gmlId", "objectId", "baumId", "gattung", "gattungLatein", "gattungDeutsch", "art", "artLatein",
            "artDeutsch", "pflanzjahr", "kronendurchmesser", "kronendmzahl", "stammumfang", "stammumfangzahl",
            "strasse", "hausnummer", "ortsteilNr", "standBearbeitung", "bezirk", "srsName", "srsDimension", "pos"})
    public HamburgStreetTreeProtobufImpl(final String gmlId,
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


        HamburgStreetTreeProtos.HamburgStreetTree.Builder builder = HamburgStreetTreeProtos.HamburgStreetTree.newBuilder()
                .setGmlId(gmlId)
                .setObjectId(objectId)
                .setBaumId(baumId)
                .setPflanzjahr(pflanzjahr)
                .setKronendurchmesser(kronendurchmesser)
                .setKronendmzahl(kronendmzahl)
                .setStammumfang(stammumfang)
                .setStammumfangzahl(stammumfangzahl)
                .setOrtsteilNr(ortsteilNr)
                .setStandBearbeitung(standBearbeitung.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond())
                .setBezirk(bezirk)
                .setSrsName(srsName)
                .setSrsDimension(srsDimension)
                .setPos(pos);

        if (gattung != null) {
            builder.setGattung(gattung);
        }

        if (gattungLatein != null) {
            builder.setGattungLatein(gattungLatein);
        }

        if (gattungDeutsch != null) {
            builder.setGattungDeutsch(gattungDeutsch);
        }

        if (art != null) {
            builder.setArt(art);
        }

        if (artLatein != null) {
            builder.setArtLatein(artLatein);
        }

        if (artDeutsch != null) {
            builder.setArtDeutsch(artDeutsch);
        }

        if (strasse != null) {
            builder.setStrasse(strasse);
        }
        if (hausnummer != null) {
            builder.setHausnummer(hausnummer);
        }

        HamburgStreetTreeProtos.HamburgStreetTree build = builder.build();

        this.storage = build.toByteArray();
    }

    private HamburgStreetTreeObjectImpl wrap() {
        try {
            HamburgStreetTreeProtos.HamburgStreetTree proto = HamburgStreetTreeProtos.HamburgStreetTree.parseFrom(storage);


            return new HamburgStreetTreeObjectImpl(proto.getGmlId(), proto.getObjectId(), proto.getBaumId(),
                    proto.getGattung(), proto.getGattungLatein(), proto.getGattungDeutsch(), proto.getArt(),
                    proto.getArtLatein(), proto.getArtDeutsch(), proto.getPflanzjahr(), proto.getKronendurchmesser(),
                    proto.getKronendmzahl(), proto.getStammumfang(), proto.getStammumfangzahl(), proto.getStrasse(),
                    proto.getHausnummer(), (short) proto.getOrtsteilNr(), LocalDateTime.ofInstant(Instant.ofEpochSecond(proto.getStandBearbeitung()), ZoneId.of("UTC")).toLocalDate(), proto.getBezirk(),
                    proto.getSrsName(), proto.getSrsDimension(), proto.getPos());
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
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
