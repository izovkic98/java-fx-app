package main.java.hr.java.covidportal.main;

import main.java.hr.java.covidportal.enumeracija.VrijednostSimptoma;
import main.java.hr.java.covidportal.iznimke.BolestIstihSimptomaException;
import main.java.hr.java.covidportal.iznimke.DuplaBolestException;
import main.java.hr.java.covidportal.iznimke.DuplikatKontaktiraneOsobeException;
import main.java.hr.java.covidportal.iznimke.NeispravanNazivException;
import main.java.hr.java.covidportal.model.*;
import main.java.hr.java.covidportal.sort.CovidSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Obradjuje glavni dio programa, te sadrzi sve vazne metode koje se implementiraju
 * u main metodi ove klase. Sluzi za skupljanje podataka o zarazenim osobama, te podatke
 * o samoj bolesti doticnih.
 *
 * @author Ivan
 */

//PROMIJENJENA VERZIJA, NE GLEDATI OVU GLAVNU !!!!

public class Glavna {

    public static final int BROJ_ITERACIJA = 3;
    public static final int BROJ_BOLESTI = 4;
    public static Scanner unos = new Scanner(System.in);
    public static Map<Bolest, List<Osoba>> mapaZarazenih = new HashMap<>();
    public static Map<String, SortedSet<Simptom>> mapaSimptoma = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public enum Karina{
        KARINA("penis", 500);

        private final String karina;
        private final int velicina;

        Karina(String karina, int velicina) {
            this.karina = karina;
            this.velicina = velicina;
        }

        public String getKarina() {
            return karina;
        }

        public int getVelicina() {
            return velicina;
        }
    }

    public static void main(String[] args) {


        Stream.of(1,2,3,4,5,6,7,8,9,10).dropWhile(i -> i < 5).forEach(System.out::println);
        System.out.println();
        Stream.of(1,2,3,4,5,6,7,8,9,10).takeWhile(i -> i < 5).forEach(System.out::println);

        System.out.println(Karina.KARINA.getVelicina());

//        System.out.println();
//
////        Collections
//        List<Osoba> osobe;
//        List<Zupanija> zupanija;
//        List<Bolest> bolest;
//        List<Simptom> simptom;
//
////        Pozivi metoda
//        zupanija = pozivZupanija();
//        simptom = pozivSimptoma();
//        bolest = pozivBolesti(simptom);
//        osobe = pozivOsoba(zupanija, bolest);
//        ispisOsoba(osobe, zupanija, simptom);


    }

    /**
     * Generira i vraca Array zupanija.
     *
     * @return Popis Zupanija.
     * @throws InputMismatchException Baca iznimku kada je krivi format unesenog podatka.
     */

    public static List<Zupanija> pozivZupanija() {

        logger.debug("Unutar metode pozivZupanija.");

        List<Zupanija> zupanije = new ArrayList<>();
        String naziv;
        int brojStanovnika = 0;
        boolean ponoviPetlju;

        System.out.print("Unesite broj županija koje želite unijeti: ");
        int brZupanija = unos.nextInt();
        unos.nextLine();

        String ternarniZ = (brZupanija == 1 ? " županiji: " : " županije: ");
        System.out.println("Unesite podatke o " + brZupanija + ternarniZ);

        for (int i = 0; i < brZupanija; i++) {

            System.out.print("Unesite naziv " + (i + 1) + ". županije: ");
            naziv = unos.nextLine();

            do {
                try {
                    System.out.print("Unesite broj stanovnika: ");
                    brojStanovnika = unos.nextInt();
                    unos.nextLine();
                    ponoviPetlju = false;

                } catch (InputMismatchException e) {
                    System.out.println("Pogrešan unos. Ponovi.");
                    unos.nextLine();
                    ponoviPetlju = true;
                    logger.error("Krivo unesena vrijednost", e);
                }
            } while (ponoviPetlju);

            System.out.print("Unesite broj zaraženih: ");
            int brojZarazenih = unos.nextInt();
            unos.nextLine();

            zupanije.add(new Zupanija(naziv, brojStanovnika, brojZarazenih));
        }
        logger.info("Unesene su županije");
        return zupanije;
    }

    /**
     * Sluzi za unos simptoma osoba.
     *
     * @return Vraca popis simptoma.
     */

    public static List<Simptom> pozivSimptoma() {

        logger.debug("Unutar metode pozivSimptoma.");

        List<Simptom> simptomi = new ArrayList<>();
        String naziv;
        String vrijednost;
        String sifra;

        System.out.print("Unesite broj simptoma koje želite unijeti: ");
        int brSimptoma = unos.nextInt();
        unos.nextLine();

        String ternarniS = (brSimptoma == 1 ? ". simptom: " : ". simptoma: ");
        System.out.println("Unesite podatke o " + brSimptoma + ternarniS);

        for (int i = 0; i < brSimptoma; i++) {

            System.out.print("Unesite naziv " + (i + 1) + ". simptoma: ");
            naziv = unos.nextLine();

//            4.LAB, 5.ZAD
            System.out.print("Unesite vrijednost " + (i + 1) + ". simptoma " + " ("
                    + VrijednostSimptoma.RIJETKO.getVrijednost() + ", "
                    + VrijednostSimptoma.SREDNJE.getVrijednost() + ", "
                    + VrijednostSimptoma.ČESTO.getVrijednost() + "):");

            vrijednost = unos.nextLine();

            if (vrijednost.equals("RIJETKO")) {
                vrijednost = VrijednostSimptoma.RIJETKO.getVrijednost();
            } else if (vrijednost.equals("SREDNJE")) {
                vrijednost = VrijednostSimptoma.SREDNJE.getVrijednost();
            } else if (vrijednost.equals("ČESTO") || vrijednost.equals("CESTO")) {
                vrijednost = VrijednostSimptoma.ČESTO.getVrijednost();
            }

            System.out.print("Unesite opis " + (i + 1) + ". simptoma: ");
            String opis = unos.nextLine();



            System.out.print("Unesite šifru " + (i + 1) + ". simptoma: ");
            sifra = unos.nextLine();



            Simptom trenutniSimptom = new Simptom(naziv, vrijednost, sifra,opis);
            simptomi.add(trenutniSimptom);


            if (!mapaSimptoma.containsKey(naziv)) {
                mapaSimptoma.put(naziv, new TreeSet<>());
            }
            mapaSimptoma.get(naziv).add(trenutniSimptom);

            logger.info("Unesen simptom " + simptomi.get(i));
        }

        logger.info("Uneseni su simptomi.");

        return simptomi;
    }

    /**
     * Sluzi za unos bolesti osoba.
     *
     * @param simptomi Simptomi bolesti ili virusa.
     * @return Vraca popis bolesti.
     */

    public static List<Bolest> pozivBolesti(List<Simptom> simptomi) {

        logger.debug("Unutar metode pozivBolesti");
        logger.warn("Pripazite na unos podataka, može biti virus ili bolest ");

        System.out.print("Unesite broj bolesti koje želite unijeti: ");
        int brBolesti = unos.nextInt();

        System.out.print("Unesite broj virusa koje želite unijeti: ");
        int brVirusa = unos.nextInt();

        int ukBolesti = brBolesti + brVirusa;

        System.out.println("Unesite podatke o " + ukBolesti + ". bolesti ili virusa: ");

        List<Bolest> bolesti = new ArrayList<>();
        int brojSimptoma = 0;
        String naziv = null;
        int odabir = 0;
        boolean ponoviPetlju;
        boolean ponoviPetlju2;
        int odabirZaraze = 0;
        boolean buffer;

        for (int i = 0; i < BROJ_BOLESTI; i++) {

            buffer = true;

            System.out.println("Unosite li bolest ili virus?");
            System.out.println("1) BOLEST\n2) VIRUS");

            do {
                try {
                    System.out.print("ODABIR >> ");
                    odabirZaraze = unos.nextInt();
                    ponoviPetlju = false;
                } catch (InputMismatchException e) {
                    System.out.println("Pogrešan unos. Ponovi.");
                    unos.nextLine();
                    ponoviPetlju = true;
                    logger.error("Krivo unesena vrijednost.", e);
                }
            } while (ponoviPetlju);

            do {

                try {

                    do {
                        try {
                            System.out.print("Unesite naziv " + (i + 1) + ". bolesti ili virusa: ");
                            if (buffer) {
                                unos.nextLine();
                            }
                            naziv = unos.nextLine();

                            provjeraNazivaBolesti(naziv);
                            provjeraDuplikataBolesti(naziv, bolesti);

                            ponoviPetlju = false;
                        } catch (NeispravanNazivException | DuplaBolestException e) {

                            logger.error("Krivo unesena vrijednost.", e);
                            System.out.println(e.getMessage());
                            buffer = false;
                            ponoviPetlju = true;
                        }

                    } while (ponoviPetlju);


                    do {
                        try {
                            System.out.print("Unesite broj simptoma: ");
                            brojSimptoma = unos.nextInt();
                            unos.nextLine();
                            ponoviPetlju = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Pogrešan unos. Ponovi.");
                            unos.nextLine();
                            ponoviPetlju = true;
                            logger.error("Krivo unesena vrijednost.", e);
                        }
                        if (brojSimptoma < 0 || brojSimptoma > BROJ_ITERACIJA) {
                            System.out.println("Neispravan unos, molimo pokušajte ponovno!");
                        }
                    } while ((brojSimptoma < 0 || brojSimptoma > BROJ_ITERACIJA) || ponoviPetlju);


                    List<Simptom> simptomiBolesti = new ArrayList<>();

                    for (int j = 0; j < brojSimptoma; j++) {
                        int redBr = 1;
                        System.out.println("Odaberite " + (j + 1) + ". simptom:");

                        for (Simptom simptom : simptomi) {
                            System.out.println(redBr + ". " + simptom.getNaziv() + " " + simptom.getVrijednost());
                            redBr++;
                        }

                        do {
                            try {
                                System.out.print("Odabir : ");
                                odabir = unos.nextInt();
                                int trenutniIndex = 0;
                                Simptom trenutniSimptom;
                                Iterator<Simptom> simptomIterator = simptomi.iterator();

                                while (simptomIterator.hasNext()) {

                                    trenutniSimptom = simptomIterator.next();

                                    if (trenutniIndex == (odabir - 1)) {
                                        simptomiBolesti.add(trenutniSimptom);
                                        break;
                                    }

                                    trenutniIndex++;

                                }

                                if ((j + 1) == brojSimptoma) {
                                    provjeraSimptoma(simptomiBolesti, bolesti);
                                }

                                ponoviPetlju = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Pogrešan unos. Ponovi.");
                                unos.nextLine();
                                ponoviPetlju = true;
                                logger.error("Krivo unesena vrijednost.", e);
                            }
                            if (odabir < 0 || odabir > BROJ_ITERACIJA) {
                                System.out.println("Neispravan unos, molimo pokušajte ponovno!");
                            }

                        } while ((odabir < 0 || odabir > BROJ_ITERACIJA) || ponoviPetlju);

                    }
                    if (odabirZaraze == 1) {
                        bolesti.add(new Bolest(naziv, simptomiBolesti));
                    } else {
                        bolesti.add(new Virus(naziv, simptomiBolesti));
                    }
                    ponoviPetlju2 = false;

                } catch (BolestIstihSimptomaException e) {
                    System.out.println(e.getMessage());
                    unos.nextLine();
                    buffer = false;
                    ponoviPetlju2 = true;
                    logger.error("Krivo unesena Bolest.", e);
                }


            } while (ponoviPetlju2);

        }
        logger.debug("Popis bolesti je napravljen");
        return bolesti;
    }

    /**
     * Provjerava da li se uneseni naziv podudara sa nekom drugom bolesti iz baze podataka.
     *
     * @param naziv   Ime bolesti.
     * @param bolesti Popis bolesti iz baze podataka.
     */

    private static void provjeraDuplikataBolesti(String naziv, List<Bolest> bolesti) {

        logger.info("unutar metode : provjeraDuplikataBolesti");

        petlja:
        for (Bolest bolestiI : bolesti) {
            if (bolestiI == null) {
                break petlja;
            }

            if (bolestiI.getNaziv().equals(naziv)) {
                throw new DuplaBolestException("Duplikat!\n" +
                        "Ponovi unos.");
            }

        }
    }


    /**
     * Provjerava da li unesena bolest ima dopusten broj znamenki.
     *
     * @param naziv Ime bolesti.
     * @throws NeispravanNazivException Baca iznimku kada je naziv neispravnog formata.
     */


    private static void provjeraNazivaBolesti(String naziv) throws NeispravanNazivException {


        logger.info("unutar metode : provjeraNazivaBolesti");

        String regex = "^[a-zA-Z]{2,15}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(naziv);

        if (!matcher.matches()) {
            throw new NeispravanNazivException("Neispravan naziv!\n" +
                    "Ponovi unos.");
        }

    }

    /**
     * Provjerava da li su već uneseni bolest ili virus s istim simptomima.
     *
     * @param simptomi Popis simptoma iz baze podataka.
     * @param bolesti  Popis bolesti iz baze podataka.
     */

    private static void provjeraSimptoma(List<Simptom> simptomi, List<Bolest> bolesti) {

        petlja:
        for (Bolest bolestiI : bolesti) {
            if (bolestiI == null) {
                break petlja;
            }

            if ((simptomi.size() == bolestiI.getSimptomi().size())) {
                if (simptomi.equals(bolestiI.getSimptomi())) {
                    throw new BolestIstihSimptomaException("Pogrešan unos, već ste unijeli bolest ili virus s istim simptomima. Molimo ponovite unos.");
                }
            }

        }
    }

    /**
     * Sastavlja popis osoba sa odredjenim podacima i vraca ga nazad.
     *
     * @param zupanija Popis zupanija iz baze podataka.
     * @param bolest   Popis bolesti iz baze podataka.
     * @return Vraca popis osoba.
     */

    public static List<Osoba> pozivOsoba(List<Zupanija> zupanija, List<Bolest> bolest) {

        String ime;
        String prezime;
        int starost = 0;
        int brOsoba = 0;
        int kontaktBr = 0;
        int odabirZupanije = 0;
        int odabirBolesti = 0;
        boolean ponoviPetlju;
        Zupanija odabranaZupanija = null;
        Bolest odabranaBolest = null;
        List<Osoba> osoba = new ArrayList<>();
        List<Osoba> kontaktOsobe = new ArrayList<>();

        logger.debug("Unutar metode pozivOsoba");

        System.out.print("Unesite broj osoba: ");
        int brUnosaOsoba = unos.nextInt();


        for (int i = 0; i < brUnosaOsoba; i++) {

            if (i > 0) {
                boolean flag = true;

                System.out.print("Unesite ime " + (i + 1) + ". osobe:");
                unos.nextLine();
                ime = unos.nextLine();

                loop:
                while (flag) {
                    for (int j = 0; j < osoba.size(); j++) {
                        if (osoba.get(j) == null) {
                            break loop;
                        }

                        while (osoba.get(j).getIme().equals(ime)) {
                            System.out.print("Unesite ime " + (i + 1) + ". osobe:");
                            ime = unos.nextLine();
                        }

                        flag = false;
                    }
                }

            } else {
                System.out.print("Unesite ime " + (i + 1) + ". osobe:");
                unos.nextLine();
                ime = unos.nextLine();
            }


            if (i > 0) {


                boolean flag = true;
                System.out.print("Unesite prezime " + (i + 1) + ". osobe:");
                prezime = unos.nextLine();

                loop:
                while (flag) {
                    for (int j = 0; j < osoba.size(); j++) {
                        if (osoba.get(j) == null) {
                            break loop;
                        }

                        while (osoba.get(j).getPrezime().equals(prezime)) {
                            System.out.print("Unesite prezime " + (i + 1) + ". osobe:");
                            prezime = unos.nextLine();

                        }
                        flag = false;
                    }
                }


            } else {
                System.out.print("Unesite prezime " + (i + 1) + ". osobe:");
                prezime = unos.nextLine();
            }


            do {
                try {
                    System.out.print("Unesite starost " + (i + 1) + ". osobe:");
                    starost = unos.nextInt();
                    ponoviPetlju = false;
                } catch (InputMismatchException e) {
                    System.out.println("Pogrešan unos. Ponovi.");
                    ponoviPetlju = true;
                    unos.nextLine();
                    logger.error("Krivo unesena vrijednost", e);
                }

            } while (ponoviPetlju);


            System.out.println("Unesite županiju prebivališta " + (i + 1) + ". osobe:");

            do {

                int brojacZupa = 1;
                for (Zupanija zupanijaOut : zupanija) {
                    System.out.println(brojacZupa + ". " + zupanijaOut.getNaziv());
                    brojacZupa++;
                }

                try {
                    System.out.print("Odabir: ");
                    odabirZupanije = unos.nextInt();
                    ponoviPetlju = false;
                } catch (InputMismatchException e) {
                    System.out.println("Pogrešan unos. Ponovi.");
                    unos.nextLine();
                    ponoviPetlju = true;
                    logger.error("Krivo unesena vrijednost", e);
                }


                if (odabirZupanije < 0 || odabirZupanije > BROJ_ITERACIJA) {
                    System.out.println("Neispravan unos, molimo pokušajte ponovno!");
                } else {

                    Iterator<Zupanija> zupanijaIterator = zupanija.iterator();

                    int trenutniIndex = 0;
                    Zupanija trenutnaZupanija;

                    while (zupanijaIterator.hasNext()) {

                        trenutnaZupanija = zupanijaIterator.next();

                        if (trenutniIndex == (odabirZupanije - 1)) {
                            odabranaZupanija = trenutnaZupanija;
                            break;
                        }
                        trenutniIndex++;
                    }
                }

            } while ((odabirZupanije < 0 || odabirZupanije > BROJ_ITERACIJA) || ponoviPetlju);

            System.out.println("Unesite bolest osobe: ");

            do {

                int brojacBolest = 1;
                for (Bolest bolestOut : bolest) {
                    System.out.println(brojacBolest + ". " + bolestOut.getNaziv());
                    brojacBolest++;
                }

                try {
                    System.out.print("Odabir: ");
                    odabirBolesti = unos.nextInt();
                    ponoviPetlju = false;
                } catch (InputMismatchException e) {
                    System.out.println("Pogrešan unos. Ponovi.");
                    unos.nextLine();
                    ponoviPetlju = true;
                    logger.error("Krivo unesena vrijednost", e);
                }

                if (odabirBolesti < 0 || odabirBolesti > BROJ_BOLESTI) {
                    System.out.println("Neispravan unos, molimo pokušajte ponovno!");
                } else {

                    Iterator<Bolest> bolestIterator = bolest.iterator();
                    int trenutniIndex = 0;
                    Bolest trenutnaBolest;

                    while (bolestIterator.hasNext()) {

                        trenutnaBolest = bolestIterator.next();

                        if (trenutniIndex == (odabirBolesti - 1)) {
                            odabranaBolest = trenutnaBolest;
                            break;
                        }
                        trenutniIndex++;
                    }
                }

            } while ((odabirBolesti < 0 || odabirBolesti > BROJ_BOLESTI) || ponoviPetlju);


            if (i > 0) {

                do {
                    try {
                        System.out.print("Unesite broj osoba koje su bile u kontaktu s tom osobom: ");
                        brOsoba = unos.nextInt();
                        if (!(brOsoba <= i && brOsoba > 0)) {
                            System.out.println("Pogrešan unos. Ponovi.");
                            ponoviPetlju = true;
                        } else {
                            ponoviPetlju = false;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Pogrešan unos. Ponovi.");
                        unos.nextLine();
                        ponoviPetlju = true;
                        logger.error("Krivo unesena vrijednost", e);
                    }

                } while (ponoviPetlju);

                kontaktOsobe = new ArrayList<>();
                System.out.println("Unesite osobe koje su bile u kontaktu s tom osobom:");

                for (int j = 0; j < brOsoba; j++) {


                    do {
                        try {
                            System.out.println("Odaberite " + (j + 1) + ". osobu:");


                            for (int k = 0; k < osoba.size(); k++) {
                                System.out.println((osoba.indexOf(osoba.get(k)) + 1) + ". " + osoba.get(k).getIme() + " " + osoba.get(k).getPrezime());
                            }

                            System.out.print("Odabir: ");
                            kontaktBr = unos.nextInt();
                            if ((j + 1) == brOsoba) {
                                provjeraOsobe(osoba.get(kontaktBr - 1), kontaktOsobe);
                            }
                            ponoviPetlju = false;

                        } catch (DuplikatKontaktiraneOsobeException e) {
                            System.out.println(e.getMessage());
                            unos.nextLine();
                            ponoviPetlju = true;
                            logger.error("Krivo unesena Osoba", e);

                        } catch (InputMismatchException e) {
                            System.out.println("Pogrešan unos. Ponovi.");
                            unos.nextLine();
                            ponoviPetlju = true;
                            logger.error("Krivo unesena vrijednost", e);
                        }

                    } while (ponoviPetlju);

                    kontaktOsobe.add(osoba.get(kontaktBr - 1));
                    logger.info("kontakt " + osoba.get(kontaktBr - 1).getIme() + " je upisan");
                }

            }

            if (i > 0) {

                osoba.add(i, new Osoba.BuilderOsobe()
                        .setIme(ime)
                        .setPrezime(prezime)
                        .setStarost(starost)
                        .setZupanija(odabranaZupanija)
                        .setZarazenBolescu(odabranaBolest)
                        .setKontaktiraneOsobe(kontaktOsobe)
                        .build());

            } else {

                osoba.add(i, new Osoba.BuilderOsobe()
                        .setIme(ime)
                        .setPrezime(prezime)
                        .setStarost(starost)
                        .setZupanija(odabranaZupanija)
                        .setZarazenBolescu(odabranaBolest)
                        .build());

            }


            logger.trace("Build osobe" + osoba.get(i));
        }


        logger.debug("Uspjesno unesene osobe.");

        return osoba;
    }

    /**
     * Provjerava ima li unutar kontaktiranih osoba duplikat.
     *
     * @param osoba        Kontaktirana osoba.
     * @param kontaktOsobe Popis kontaktiranih osoba iz baze podataka.
     * @throws DuplikatKontaktiraneOsobeException Baca iznimku ako ju je zatrazen unos dviju istih kontaktiranih osoba.
     */

    private static void provjeraOsobe(Osoba osoba, List<Osoba> kontaktOsobe) throws DuplikatKontaktiraneOsobeException {

        logger.debug("Unutar metode provjeraOsobe");

        for (int i = 0; i < kontaktOsobe.size(); i++) {
            if (osoba.equals(kontaktOsobe.get(i))) {
                throw new DuplikatKontaktiraneOsobeException("Odabrana osoba se već nalazi među kontaktiranim osobama." +
                        "\nMolimo Vas da odaberete neku drugu osobu.");
            }

        }
    }


    /**
     * Ispisuje popis zarazenih osoba.
     *
     * @param osobe    Popis osoba iz baze podataka.
     * @param zupanija Popis zupanija iz baze podataka.
     */

    private static void ispisOsoba(List<Osoba> osobe, List<Zupanija> zupanija, List<Simptom> simptomi) {

        logger.info("Unutar metode ispisOsoba");

        System.out.println("Popis osoba: ");

        for (int i = 0; i < osobe.size(); i++) {
            logger.info("Osobe su: " + osobe.get(i).getIme() + " " + osobe.get(i).getPrezime());
            System.out.println("Ime i prezime: " + osobe.get(i).getIme() + " " + osobe.get(i).getPrezime());
            System.out.println("Starost: " + osobe.get(i).getStarost());
            System.out.println("Županija prebivališta: " + osobe.get(i).getZupanija());
            System.out.println("Zaražen bolešću: " + osobe.get(i).getZarazenBolescu());

            if (i > 0) {

                System.out.println("Kontaktirane osobe:");
                for (Osoba kontaktOsobe : osobe.get(i).getKontaktiraneOsobe()) {
                    logger.info("kontakt osobe su: " + kontaktOsobe.getIme() + " " + kontaktOsobe.getPrezime());
                    System.out.println(kontaktOsobe.getIme() + " " + kontaktOsobe.getPrezime());

                }
            } else {
                System.out.println("Nema kontaktiranih osoba.");
            }

//            4. LAB, 6 ZAD

            if (!mapaZarazenih.containsKey(osobe.get(i).getZarazenBolescu())) {
                mapaZarazenih.put(osobe.get(i).getZarazenBolescu(), new ArrayList());
            }
            mapaZarazenih.get(osobe.get(i).getZarazenBolescu()).add(osobe.get(i));

        }


        for (Bolest bolest : mapaZarazenih.keySet()) {
            String msg;
            msg = bolest instanceof Virus ? "virusa " : "bolesti ";
            System.out.println("Od " + msg + bolest.getNaziv() + " boluju: ");

            for (Osoba osoba : mapaZarazenih.get(bolest)) {
                System.out.println(osoba.getIme() + " " + osoba.getPrezime());
            }

        }

//        4. LAB, 10 ZAD

//        System.out.println("Najviše zaraženih osoba ima u županiji " + zupanija.first().getNaziv() + ": " + zupanija.first().getPostotak() * 100 + "%");

//        LAB. VJEŽBA


        System.out.println("Sortirani simptomi: ");
        Function<Simptom, String> poNazivu = Simptom::getNaziv;
        Function<Simptom, String> poSifri = Simptom::getSifra;

        Comparator<Simptom> poNazivuISifri =
                Comparator.comparing(poNazivu).thenComparing(poSifri);

        Comparator<Simptom> poSifriINazivu =
                Comparator.comparing(poSifri).thenComparing(poNazivu);

        simptomi.stream().sorted(poNazivuISifri).forEach(System.out::println);

        Simptom prviSimptom = simptomi.stream().min(poNazivuISifri).get();
        Simptom zadnjiSimptom = simptomi.stream().max(poSifriINazivu).get();


        System.out.println("Prvi simptom po abecednom redu naziva pa šifre: " + prviSimptom.getNaziv());
        System.out.println("Zadnji simptom po abecednom redu šifre pa naziva: " + zadnjiSimptom.getNaziv());


        System.out.println("Mapa sa simptomima: ");

        int redBr = 1;
        for (String kljucSimptoma : mapaSimptoma.keySet()) {

            System.out.println(redBr + "." + " Grupa je: ");
            for (Simptom simptom : mapaSimptoma.get(kljucSimptoma)) {

                System.out.println("Naziv: " + simptom.getNaziv() + " || Šifra: " + simptom.getSifra());
            }

            redBr++;
        }


        logger.info("POPIS OSOBA ISPISAN!!!");
    }
}






