package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.JenjangConstant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author muhsin
 */
@Entity @Table(name = "program_studi")
@Data
public class ProgramStudi {
    @Id @GeneratedValue(generator ="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nama;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String keterangan;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private JenjangConstant jenjang;

    @ManyToOne
    @JoinColumn(name = "id_fakultas")
    Fakultas fakultas;

}