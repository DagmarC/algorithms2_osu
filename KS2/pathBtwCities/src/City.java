import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

// City is a DTO (Data Transfer Object).
// From Java 14/16 instead of using Class we can use record.
// All fields are automatically private and final.
// instead of calling city.getName() use city.name()
// equals() and hashCode() compares data (ID and name) not object references.
// toString(): City[id=..., name=A, ...]

public record City(
        @JsonProperty("ID") UUID id,
        @JsonProperty("Name") String name,
        @JsonProperty("ParentID") UUID parentId,
        @JsonProperty("ChildrenIDs") List<UUID> childrenIds
) {

    public City {
        if (childrenIds == null) {
            childrenIds = List.of();
        }
    }
}