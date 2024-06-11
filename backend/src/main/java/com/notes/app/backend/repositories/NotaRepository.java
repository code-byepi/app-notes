package com.notes.app.backend.repositories;

import com.notes.app.backend.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByCreationTimeBefore(LocalDateTime time);

    Optional<Nota> findById(Long noteId);

    @Query("SELECT n FROM Nota n WHERE n.user.userId = :userId") // Modificar la consulta
    List<Nota> findByUserId(@Param("userId") Long userId);

    @Query("SELECT n FROM Nota n WHERE n.user.userId = :userId ORDER BY n.creationTime DESC")
    List<Nota> findTop10ByUserIdOrderByCreationTimeDesc(@Param("userId") Long userId);

    @Modifying
    @Query("delete from Nota cu where cu.noteId=?1")
    void deleteNotaUsuarioPorId(Long id);
}
