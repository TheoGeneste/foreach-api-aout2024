package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Cours;
import com.example.services.EtudiantService;
import com.example.services.FormateurService;
import com.example.services.UEService;

@Component
public class CoursWrapper implements RowMapper<Cours> {
    @Autowired
    private UEService ueService;
    @Autowired
    private FormateurService formateurService;
    @Autowired
    private EtudiantService etudiantService;

    
    // @Autowired
    // public CoursWrapper(UEService ueService, FormateurService formateurService, EtudiantService etudiantService) {
    //     this.ueService = ueService;
    //     this.formateurService = formateurService;
    //     this.etudiantService = etudiantService;
    // }



    @Override
    public Cours mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cours(rs.getInt("Id"),
        rs.getDate("Date_Debut"),
        rs.getDate("Date_Fin"),
        this.ueService.getByID(rs.getInt("FK_UE")),
        this.formateurService.getByID(rs.getInt("FK_Formateur")),
        this.etudiantService.getByCourID(rs.getInt("Id"))
        );
    }
    
}
