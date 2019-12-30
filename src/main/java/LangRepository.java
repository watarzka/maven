import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {
    private List<Lang> languages;

    public LangRepository() {
        this.languages = new ArrayList<>();
        this.languages.add(new Lang(1L,"Hello","en"));
        this.languages.add(new Lang(2L,"Siemanko","pl"));
    }
    Optional<Lang> findById(Long id)
    {
        return languages.stream().filter(l->l.getId().equals(id)).findFirst();
    }
}
