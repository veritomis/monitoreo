

@Entity
@Table(name = "boya")
public class Boya {
    @Id
    @Column(name = "boya_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boyaId;
    @Column(name = "color_luz")
    private String colorLuz = "AZUL";
    @Column(name = "latitud_instalacion")
    private double latitudInstalacion;
    @Column(name = "longitud_instalacion")
    private double longitudInstalacion;
    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Muestra> muestras = new ArrayList<>();

    public String obtenerColor(double nivelMar) {
        colorLuz = "VERDE";

        if (Math.abs(nivelMar) >= 100 || Math.abs(nivelMar) <= -100) {
            return colorLuz = "ROJO";
        } else if (Math.abs(nivelMar) >= 50 || Math.abs(nivelMar) <= -50) {
            return colorLuz = "AMARILLO";
        }

        return colorLuz;
    }

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }