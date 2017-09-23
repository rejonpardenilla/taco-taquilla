package DataAccess.Interfaces;

import Elements.Play;

import java.util.List;

public interface PlayDaoInterface {
    List<Play> findAll();
    Play findById(int id);
}
