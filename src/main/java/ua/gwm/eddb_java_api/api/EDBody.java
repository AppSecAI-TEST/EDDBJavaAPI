package ua.gwm.eddb_java_api.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ua.gwm.eddb_java_api.EDDBJavaAPI;
import ua.gwm.eddb_java_api.utils.EDDBJavaAPIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;
import java.util.function.Predicate;

public class EDBody {

    public static URL bodies_url;
    public static File bodies_file;
    private static boolean updated = false;

    static {
        try {
            bodies_url = new URL("https://eddb.io/archive/v5/bodies.jsonl");
            bodies_file = new File(EDDBJavaAPI.OPTIONS.getDirectory(), "bodies.jsonl");
            if (!bodies_file.exists()) {
                bodies_file.createNewFile();
                updateOrDownloadBodies();
            }
        } catch (Exception e) {
            System.out.println("Exception on EDBody static initialization...");
            e.printStackTrace();
        }
    }

    public static boolean isUpdated() {
        return updated;
    }

    public static void allowUpdate() {
        updated = false;
    }

    public static void updateOrDownloadBodies() {
        if (updated) {
            return;
        }
        try {
            System.out.println("Starting bodies download.");
            ReadableByteChannel rbc = Channels.newChannel(bodies_url.openStream());
            FileOutputStream fos = new FileOutputStream(bodies_file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            updated = true;
            System.out.println("Bodies successfully downloaded!");
        } catch (Exception e) {
            System.out.println("Exception on downloading bodies...");
            e.printStackTrace();
        }
    }

    public static Set<EDBody> loadBodies(Predicate<EDBody> predicate) {
        System.out.println("Starting bodies loading.");
        try {
            Set<EDBody> bodies = new HashSet<EDBody>();
            BufferedReader reader = new BufferedReader(new FileReader(bodies_file));
            String readed;
            while ((readed = reader.readLine()) != null && !readed.equals("")) {
                JsonObject body_object = EDDBJavaAPIUtils.GSON.fromJson(readed, JsonObject.class);
                int id = body_object.get("id").getAsJsonPrimitive().getAsInt();
                long created_at = body_object.get("created_at").getAsJsonPrimitive().getAsLong();
                long updated_at = body_object.get("updated_at").getAsJsonPrimitive().getAsLong();
                String name = body_object.get("name").getAsJsonPrimitive().getAsString();
                int system_id = body_object.get("system_id").getAsJsonPrimitive().getAsInt();
                JsonElement group_element = body_object.get("group_name");
                EDBodyGroup group = group_element.isJsonNull() ?
                        EDBodyGroup.UNKNOWN :
                        EDBodyGroup.byName(group_element.getAsJsonPrimitive().getAsString());
                JsonElement type_element = body_object.get("type_name");
                EDBodyType type = type_element.isJsonNull() ?
                        EDBodyType.UNKNOWN :
                        EDBodyType.byName(type_element.getAsJsonPrimitive().getAsString());
                JsonElement distance_to_arrival_element = body_object.get("distance_to_arrival");
                int distance_to_arrival = distance_to_arrival_element.isJsonNull() ? -1 :
                        distance_to_arrival_element.getAsJsonPrimitive().getAsInt();
                JsonElement full_spectral_class_element = body_object.get("full_spectral_class");
                String full_spectral_class = full_spectral_class_element.isJsonNull() ?
                        null :
                        full_spectral_class_element.getAsJsonPrimitive().getAsString();
                JsonElement spectral_class_element = body_object.get("spectral_class");
                String spectral_class = spectral_class_element.isJsonNull() ?
                        null :
                        spectral_class_element.getAsJsonPrimitive().getAsString();
                JsonElement spectral_sub_class_element = body_object.get("spectral_sub_class");
                String spectral_sub_class = spectral_sub_class_element.isJsonNull() ?
                        null :
                        spectral_sub_class_element.getAsJsonPrimitive().getAsString();
                JsonElement luminosity_class_element = body_object.get("luminosity_class");
                String luminosity_class = luminosity_class_element.isJsonNull() ?
                        null :
                        luminosity_class_element.getAsJsonPrimitive().getAsString();
                JsonElement luminosity_sub_class_element = body_object.get("luminosity_sub_class");
                String luminosity_sub_class = luminosity_sub_class_element.isJsonNull() ?
                        null :
                        luminosity_sub_class_element.getAsJsonPrimitive().getAsString();
                JsonElement surface_temperature_element = body_object.get("surface_temperature");
                int surface_temperature = surface_temperature_element.isJsonNull() ?
                        -1 :
                        surface_temperature_element.getAsJsonPrimitive().getAsInt();
                JsonElement is_main_star_element = body_object.get("is_main_star");
                boolean is_main_star = !is_main_star_element.isJsonNull() &&
                        is_main_star_element.getAsJsonPrimitive().getAsBoolean();
                JsonElement age_element = body_object.get("age");
                int age = age_element.isJsonNull() ?
                        -1 :
                        age_element.getAsJsonPrimitive().getAsInt();
                JsonElement solar_masses_element = body_object.get("solar_masses");
                int solar_masses = solar_masses_element.isJsonNull() ?
                        -1 :
                        solar_masses_element.getAsJsonPrimitive().getAsInt();
                JsonElement solar_radius_element = body_object.get("solar_radius");
                int solar_radius = solar_radius_element.isJsonNull() ?
                        -1 :
                        solar_radius_element.getAsJsonPrimitive().getAsInt();
                JsonElement catalogue_gliese_id_element = body_object.get("catalogue_gliese_id");
                String catalogue_gliese_id = catalogue_gliese_id_element.isJsonNull() ||
                        catalogue_gliese_id_element.getAsJsonPrimitive().getAsString().equals("") ?
                        null :
                        catalogue_gliese_id_element.getAsJsonPrimitive().getAsString();
                JsonElement catalogue_hipp_id_element = body_object.get("catalogue_hipp_id");
                String catalogue_hipp_id = catalogue_hipp_id_element.isJsonNull() ||
                        catalogue_hipp_id_element.getAsJsonPrimitive().getAsString().equals("") ?
                        null :
                        catalogue_hipp_id_element.getAsJsonPrimitive().getAsString();
                JsonElement catalogue_hd_id_element = body_object.get("catalogue_hd_id");
                String catalogue_hd_id = catalogue_hd_id_element.isJsonNull() ||
                        catalogue_hd_id_element.getAsJsonPrimitive().getAsString().equals("") ?
                        null :
                        catalogue_hd_id_element.getAsJsonPrimitive().getAsString();
                JsonElement volcanism_type_name_element = body_object.get("volcanism_type_name");
                EDVolcanism volcanism = volcanism_type_name_element.isJsonNull() ?
                        EDVolcanism.UNKNOWN :
                        EDVolcanism.byName(volcanism_type_name_element.getAsJsonPrimitive().getAsString());
                JsonElement atmosphere_type_name_element = body_object.get("atmosphere_type_name");
                EDAtmosphere atmosphere = atmosphere_type_name_element.isJsonNull() ?
                        EDAtmosphere.UNKNOWN :
                        EDAtmosphere.byName(atmosphere_type_name_element.getAsJsonPrimitive().getAsString());
                JsonElement terraforming_state_name_element = body_object.get("terraforming_state_name");
                EDTerraformingState terraforming_state = terraforming_state_name_element.isJsonNull() ?
                        EDTerraformingState.UNKNOWN :
                        EDTerraformingState.byName(terraforming_state_name_element.getAsJsonPrimitive().getAsString());
                JsonElement earth_masses_element = body_object.get("earth_masses");
                double earth_masses = earth_masses_element.isJsonNull() ?
                        -1. :
                        earth_masses_element.getAsJsonPrimitive().getAsDouble();
                JsonElement radius_element = body_object.get("radius");
                int radius = radius_element.isJsonNull() ?
                        -1 :
                        radius_element.getAsJsonPrimitive().getAsInt();
                JsonElement gravity_element = body_object.get("gravity");
                double gravity = gravity_element.isJsonNull() ?
                        -1. :
                        gravity_element.getAsJsonPrimitive().getAsDouble();
                JsonElement surface_pressure_element = body_object.get("surface_pressure");
                BigDecimal surface_pressure = surface_pressure_element.isJsonNull() ?
                        new BigDecimal("-1") :
                        surface_pressure_element.getAsJsonPrimitive().getAsBigDecimal();
                JsonElement orbital_period_element = body_object.get("orbital_period");
                double orbital_period = orbital_period_element.isJsonNull() ?
                        -1. :
                        orbital_period_element.getAsJsonPrimitive().getAsDouble();
                JsonElement semi_major_axis_element = body_object.get("semi_major_axis");
                double semi_major_axis = semi_major_axis_element.isJsonNull() ?
                        -1. :
                        semi_major_axis_element.getAsJsonPrimitive().getAsDouble();
                JsonElement orbital_eccentricity_element = body_object.get("orbital_eccentricity");
                int orbital_eccentricity = orbital_eccentricity_element.isJsonNull() ?
                        -1 :
                        orbital_eccentricity_element.getAsJsonPrimitive().getAsInt();
                JsonElement orbital_inclination_element = body_object.get("orbital_inclination");
                double orbital_inclination = orbital_inclination_element.isJsonNull() ?
                        -1. :
                        orbital_inclination_element.getAsJsonPrimitive().getAsDouble();
                JsonElement arg_of_periapsis_element = body_object.get("arg_of_periapsis");
                double arg_of_periapsis = arg_of_periapsis_element.isJsonNull() ?
                        -1. :
                        arg_of_periapsis_element.getAsJsonPrimitive().getAsDouble();
                JsonElement rotational_period_element = body_object.get("rotational_period");
                double rotational_period = rotational_period_element.isJsonNull() ?
                        -1. :
                        rotational_period_element.getAsJsonPrimitive().getAsDouble();
                JsonElement is_rotational_period_tidally_locked_element = body_object.get("is_rotational_period_tidally_locked");
                boolean is_rotational_period_tidally_locked = !is_rotational_period_tidally_locked_element.isJsonNull() &&
                        is_rotational_period_tidally_locked_element.getAsJsonPrimitive().getAsBoolean();
                JsonElement axis_tilt_element = body_object.get("axis_tilt");
                double axis_tilt = axis_tilt_element.isJsonNull() ?
                        -1. :
                        axis_tilt_element.getAsJsonPrimitive().getAsDouble();
                JsonElement eg_id_element = body_object.get("eg_id");
                int eg_id = eg_id_element.isJsonNull() ?
                        -1 :
                        eg_id_element.getAsJsonPrimitive().getAsInt();
                JsonElement belt_moon_masses_element = body_object.get("belt_moon_masses");
                double belt_moon_masses = belt_moon_masses_element.isJsonNull() ?
                        -1. :
                        belt_moon_masses_element.getAsJsonPrimitive().getAsDouble();
                List<EDRing> rings = new ArrayList<EDRing>();
                JsonArray rings_array = body_object.get("rings").getAsJsonArray();
                for (JsonElement ring_element : rings_array) {
                    JsonObject ring_object = ring_element.getAsJsonObject();
                    JsonElement ring_ring_id_element = ring_object.get("id");
                    int ring_ring_id = ring_ring_id_element.isJsonNull() ?
                            -1 :
                            ring_ring_id_element.getAsJsonPrimitive().getAsInt();
                    JsonElement ring_created_at_element = ring_object.get("created_at");
                    long ring_created_at = ring_created_at_element.isJsonNull() ?
                            -1L :
                            ring_created_at_element.getAsJsonPrimitive().getAsInt();
                    JsonElement ring_updated_at_element = ring_object.get("updated_at");
                    long ring_updated_at = ring_updated_at_element.isJsonNull() ?
                            -1L :
                            ring_updated_at_element.getAsJsonPrimitive().getAsInt();
                    JsonElement ring_name_element = ring_object.get("name");
                    String ring_name = ring_name_element.isJsonNull() ?
                            null :
                            ring_name_element.getAsJsonPrimitive().getAsString();
                    JsonElement ring_semi_major_axis_element = ring_object.get("semi_major_axis");
                    BigDecimal ring_semi_major_axis = ring_semi_major_axis_element.isJsonNull() ?
                            new BigDecimal("-1") :
                            ring_semi_major_axis_element.getAsJsonPrimitive().getAsBigDecimal();
                    JsonElement ring_ring_mass_element = ring_object.get("ring_mass");
                    long ring_ring_mass = ring_ring_mass_element.isJsonNull() ?
                            -1L :
                            ring_ring_mass_element.getAsJsonPrimitive().getAsLong();
                    JsonElement ring_ring_inner_radius_element = ring_object.get("ring_inner_radius");
                    int ring_ring_inner_radius = ring_ring_inner_radius_element.isJsonNull() ?
                            -1 :
                            ring_ring_inner_radius_element.getAsJsonPrimitive().getAsInt();
                    JsonElement ring_ring_outer_radius_element = ring_object.get("ring_outer_radius");
                    int ring_ring_outer_radius = ring_ring_outer_radius_element.isJsonNull() ?
                            -1 :
                            ring_ring_outer_radius_element.getAsJsonPrimitive().getAsInt();
                    JsonElement ring_ring_type_element = body_object.get("ring_type_name");
                    EDRingType ring_ring_type = ring_ring_type_element.isJsonNull() ?
                            EDRingType.UNKNOWN :
                            EDRingType.byName(type_element.getAsJsonPrimitive().getAsString());
                    rings.add(new EDRing(ring_ring_id, ring_created_at, ring_updated_at, ring_name, ring_semi_major_axis,
                            ring_ring_mass, ring_ring_inner_radius, ring_ring_outer_radius, ring_ring_type));
                }
                Map<EDAtmosphereComponentType, Double> atmosphere_composition =
                        new HashMap<EDAtmosphereComponentType, Double>();
                JsonArray atmosphere_composition_array = body_object.getAsJsonArray("atmosphere_composition");
                for (JsonElement atmosphere_composition_element : atmosphere_composition_array) {
                    JsonObject atmosphere_composition_object = atmosphere_composition_element.getAsJsonObject();
                    atmosphere_composition.put(EDAtmosphereComponentType.
                            byName(atmosphere_composition_object.getAsJsonPrimitive("atmosphere_component_name").getAsString()),
                            atmosphere_composition_object.getAsJsonPrimitive("share").getAsDouble());
                }
                Map<EDSolidComponentType, Double> solid_composition =
                        new HashMap<EDSolidComponentType, Double>();
                JsonArray solid_composition_array = body_object.getAsJsonArray("solid_composition");
                for (JsonElement solid_composition_element : solid_composition_array) {
                    JsonObject solid_composition_object = solid_composition_element.getAsJsonObject();
                    solid_composition.put(EDSolidComponentType.
                            byName(solid_composition_object.getAsJsonPrimitive("solid_component_name").getAsString()),
                            solid_composition_object.getAsJsonPrimitive("share").getAsDouble());
                }
                Map<EDMaterialType, Double> materials =
                        new HashMap<EDMaterialType, Double>();
                JsonArray materials_array = body_object.getAsJsonArray("materials");
                for (JsonElement material_element : materials_array) {
                    JsonObject material_object = material_element.getAsJsonObject();
                    JsonElement share_element = material_object.get("share");
                    materials.put(EDMaterialType.
                            byName(material_object.getAsJsonPrimitive("material_name").getAsString()),
                            share_element.isJsonNull() ? -1. : share_element.getAsJsonPrimitive().getAsDouble());
                }
                boolean is_landable = body_object.get("is_landable").getAsJsonPrimitive().getAsBoolean();
                EDBody body = new EDBody(id, created_at, updated_at, name, system_id, group, type,
                        distance_to_arrival, full_spectral_class, spectral_class, spectral_sub_class,
                        luminosity_class, luminosity_sub_class, surface_temperature, is_main_star, age,
                        solar_masses, solar_radius, catalogue_gliese_id, catalogue_hipp_id,
                        catalogue_hd_id, volcanism, atmosphere, terraforming_state, earth_masses, radius,
                        gravity, surface_pressure, orbital_period, semi_major_axis, orbital_eccentricity,
                        orbital_inclination, arg_of_periapsis, rotational_period,
                        is_rotational_period_tidally_locked, axis_tilt, eg_id, belt_moon_masses, rings,
                        atmosphere_composition, solid_composition, materials, is_landable);
                if (predicate.test(body)) {
                    bodies.add(body);
                }
            }
            System.out.println("Bodies successfully loaded!");
            return bodies;
        } catch (Exception e) {
            System.out.println("Exception on loading bodies...");
            e.printStackTrace();
            return new HashSet<EDBody>();
        }
    }

    private final int id;
    private final long created_at;
    private final long updated_at;
    private final String name;
    private final int system_id;
    private final EDBodyGroup group; //group_name
    private final EDBodyType type; //type_name
    private final int distance_to_arrival;
    private final String full_spectral_class;
    private final String spectral_class;
    private final String spectral_sub_class;
    private final String luminosity_class;
    private final String luminosity_sub_class;
    private final int surface_temperature;
    private final boolean is_main_star;
    private final int age;
    private final int solar_masses;
    private final int solar_radius;
    private final String catalogue_gliese_id;
    private final String catalogue_hipp_id;
    private final String catalogue_hd_id;
    private final EDVolcanism volcanism; //String volcanism_type_name
    private final EDAtmosphere atmosphere; //String atmosphere_type_name
    private final EDTerraformingState terraforming_state; //terraforming_state_name
    private final double earth_masses;
    private final int radius;
    private final double gravity;
    private final BigDecimal surface_pressure;
    private final double orbital_period;
    private final double semi_major_axis;
    private final int orbital_eccentricity;
    private final double orbital_inclination;
    private final double arg_of_periapsis;
    private final double rotational_period;
    private final boolean is_rotational_period_tidally_locked;
    private final double axis_tilt;
    private final int eg_id;
    private final double belt_moon_masses;
    /*private final Object ring_type;
    private final Object ring_mass;
    private final Object ring_inner_radius;
    private final Object ring_outer_radius;*/
    private final List<EDRing> rings;
    private final Map<EDAtmosphereComponentType, Double> atmosphere_composition;
    private final Map<EDSolidComponentType, Double> solid_composition;
    private final Map<EDMaterialType, Double> materials;
    private final boolean is_landable;

    public EDBody(int id, long created_at, long updated_at, String name, int system_id,
                  EDBodyGroup group, EDBodyType type, int distance_to_arrival,
                  String full_spectral_class, String spectral_class, String spectral_sub_class,
                  String luminosity_class, String luminosity_sub_class, int surface_temperature,
                  boolean is_main_star, int age, int solar_masses, int solar_radius,
                  String catalogue_gliese_id, String catalogue_hipp_id, String catalogue_hd_id,
                  EDVolcanism volcanism, EDAtmosphere atmosphere, EDTerraformingState terraforming_state,
                  double earth_masses, int radius, double gravity, BigDecimal surface_pressure,
                  double orbital_period, double semi_major_axis, int orbital_eccentricity,
                  double orbital_inclination, double arg_of_periapsis, double rotational_period,
                  boolean is_rotational_period_tidally_locked, double axis_tilt, int eg_id,
                  double belt_moon_masses, /*Object ring_type, Object ring_mass,
                  Object ring_inner_radius, Object ring_outer_radius,*/ List<EDRing> rings,
                  Map<EDAtmosphereComponentType, Double> atmosphere_composition,
                  Map<EDSolidComponentType, Double> solid_composition,
                  Map<EDMaterialType, Double> materials, boolean is_landable) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.system_id = system_id;
        this.group = group;
        this.type = type;
        this.distance_to_arrival = distance_to_arrival;
        this.full_spectral_class = full_spectral_class;
        this.spectral_class = spectral_class;
        this.spectral_sub_class = spectral_sub_class;
        this.luminosity_class = luminosity_class;
        this.luminosity_sub_class = luminosity_sub_class;
        this.surface_temperature = surface_temperature;
        this.is_main_star = is_main_star;
        this.age = age;
        this.solar_masses = solar_masses;
        this.solar_radius = solar_radius;
        this.catalogue_gliese_id = catalogue_gliese_id;
        this.catalogue_hipp_id = catalogue_hipp_id;
        this.catalogue_hd_id = catalogue_hd_id;
        this.volcanism = volcanism;
        this.atmosphere = atmosphere;
        this.terraforming_state = terraforming_state;
        this.earth_masses = earth_masses;
        this.radius = radius;
        this.gravity = gravity;
        this.surface_pressure = surface_pressure;
        this.orbital_period = orbital_period;
        this.semi_major_axis = semi_major_axis;
        this.orbital_eccentricity = orbital_eccentricity;
        this.orbital_inclination = orbital_inclination;
        this.arg_of_periapsis = arg_of_periapsis;
        this.rotational_period = rotational_period;
        this.is_rotational_period_tidally_locked = is_rotational_period_tidally_locked;
        this.axis_tilt = axis_tilt;
        this.eg_id = eg_id;
        this.belt_moon_masses = belt_moon_masses;
        /*this.ring_type = ring_type;
        this.ring_mass = ring_mass;
        this.ring_inner_radius = ring_inner_radius;
        this.ring_outer_radius = ring_outer_radius;*/
        this.rings = rings;
        this.atmosphere_composition = atmosphere_composition;
        this.solid_composition = solid_composition;
        this.materials = materials;
        this.is_landable = is_landable;
    }

    public int getId() {
        return id;
    }

    public long getCreatedAt() {
        return created_at;
    }

    public long getUpdatedAt() {
        return updated_at;
    }

    public String getName() {
        return name;
    }

    public int getSystemId() {
        return system_id;
    }

    public EDBodyGroup getGroup() {
        return group;
    }

    public EDBodyType getType() {
        return type;
    }

    public int getDistanceToArrival() {
        return distance_to_arrival;
    }

    public String getFullSpectralClass() {
        return full_spectral_class;
    }

    public String getSpectralClass() {
        return spectral_class;
    }

    public String getSpectralSubClass() {
        return spectral_sub_class;
    }

    public String getLuminosityClass() {
        return luminosity_class;
    }

    public String getLuminositySubClass() {
        return luminosity_sub_class;
    }

    public int getSurfaceTemperature() {
        return surface_temperature;
    }

    public boolean isMainStar() {
        return is_main_star;
    }

    public int getAge() {
        return age;
    }

    public int getSolarMasses() {
        return solar_masses;
    }

    public int getSolarRadius() {
        return solar_radius;
    }

    public String getCatalogueGlieseId() {
        return catalogue_gliese_id;
    }

    public String getCatalogueHippId() {
        return catalogue_hipp_id;
    }

    public String getCatalogueHdId() {
        return catalogue_hd_id;
    }

    public EDVolcanism getVolcanism() {
        return volcanism;
    }

    public EDAtmosphere getAtmosphere() {
        return atmosphere;
    }

    public EDTerraformingState getTerraformingState() {
        return terraforming_state;
    }

    public double getEarthMasses() {
        return earth_masses;
    }

    public int getRadius() {
        return radius;
    }

    public double getGravity() {
        return gravity;
    }

    public BigDecimal getSurfacePressure() {
        return surface_pressure;
    }

    public double getOrbitalPeriod() {
        return orbital_period;
    }

    public double getSemiMajorAxis() {
        return semi_major_axis;
    }

    public int getOrbitalEccentricity() {
        return orbital_eccentricity;
    }

    public double getOrbitalInclination() {
        return orbital_inclination;
    }

    public double getArgOfPeriapsis() {
        return arg_of_periapsis;
    }

    public double getRotationalPeriod() {
        return rotational_period;
    }

    public boolean isRotationalPeriodTidallyLocked() {
        return is_rotational_period_tidally_locked;
    }

    public double getAxisTilt() {
        return axis_tilt;
    }

    public int getEgId() {
        return eg_id;
    }

    public double getBeltMoonMasses() {
        return belt_moon_masses;
    }

    /*public Object getRingType() {
        return ring_type;
    }

    public Object getRingMass() {
        return ring_mass;
    }

    public Object getRingInnerRadius() {
        return ring_inner_radius;
    }

    public Object getRingOuterRadius() {
        return ring_outer_radius;
    }*/

    public List<EDRing> getRings() {
        return rings;
    }

    public Map<EDAtmosphereComponentType, Double> getAtmosphereComposition() {
        return atmosphere_composition;
    }

    public Map<EDSolidComponentType, Double> getSolidComposition() {
        return solid_composition;
    }

    public Map<EDMaterialType, Double> getMaterials() {
        return materials;
    }

    public boolean isLandable() {
        return is_landable;
    }

    @Override
    public String toString() {
        return "EDBody{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", name='" + name + '\'' +
                ", system_id=" + system_id +
                ", group=" + group +
                ", type=" + type +
                ", distance_to_arrival=" + distance_to_arrival +
                ", full_spectral_class='" + full_spectral_class + '\'' +
                ", spectral_class='" + spectral_class + '\'' +
                ", spectral_sub_class='" + spectral_sub_class + '\'' +
                ", luminosity_class='" + luminosity_class + '\'' +
                ", luminosity_sub_class='" + luminosity_sub_class + '\'' +
                ", surface_temperature=" + surface_temperature +
                ", is_main_star=" + is_main_star +
                ", age=" + age +
                ", solar_masses=" + solar_masses +
                ", solar_radius=" + solar_radius +
                ", catalogue_gliese_id='" + catalogue_gliese_id + '\'' +
                ", catalogue_hipp_id='" + catalogue_hipp_id + '\'' +
                ", catalogue_hd_id='" + catalogue_hd_id + '\'' +
                ", volcanism=" + volcanism +
                ", atmosphere=" + atmosphere +
                ", terraforming_state=" + terraforming_state +
                ", earth_masses=" + earth_masses +
                ", radius=" + radius +
                ", gravity=" + gravity +
                ", surface_pressure=" + surface_pressure +
                ", orbital_period=" + orbital_period +
                ", semi_major_axis=" + semi_major_axis +
                ", orbital_eccentricity=" + orbital_eccentricity +
                ", orbital_inclination=" + orbital_inclination +
                ", arg_of_periapsis=" + arg_of_periapsis +
                ", rotational_period=" + rotational_period +
                ", is_rotational_period_tidally_locked=" + is_rotational_period_tidally_locked +
                ", axis_tilt=" + axis_tilt +
                ", eg_id=" + eg_id +
                ", belt_moon_masses=" + belt_moon_masses +
                ", rings=" + rings +
                ", atmosphere_composition=" + atmosphere_composition +
                ", solid_composition=" + solid_composition +
                ", materials=" + materials +
                ", is_landable=" + is_landable +
                '}';
    }
}
