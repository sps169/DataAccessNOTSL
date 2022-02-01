//package mapper;
//
//import dao.Repository;
//import dto.RepositoryDTO;
//
//public class RepositoryMapper extends BaseMapper<Repository, RepositoryDTO> {
//    @Override
//    public Repository fromDTO(RepositoryDTO item) {
//        return new Repository(item.getId(),item.getName(),item.getCreationDate(),item.getProject(),item.getCommits(),
//                item.getIssues());
//    }
//
//    @Override
//    public RepositoryDTO toDTO(Repository item) {
//        return new RepositoryDTO(item.getId(),item.getName(),item.getCreationDate(),item.getProject(),item.getCommits(),
//                item.getIssues());
//    }
//}
