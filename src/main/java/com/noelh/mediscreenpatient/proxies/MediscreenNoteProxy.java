package com.noelh.mediscreenpatient.proxies;

import com.noelh.mediscreenpatient.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MediscreenNote", url = "${MediscreenNoteUrl}")
public interface MediscreenNoteProxy {

    @DeleteMapping("patientId/{PatientId}")
    List<NoteBean> deleteNoteBeanByPatientId(@PathVariable("PatientId") Long id);
}
