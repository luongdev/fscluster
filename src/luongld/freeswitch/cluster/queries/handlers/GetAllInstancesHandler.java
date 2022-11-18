package luongld.freeswitch.cluster.queries.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.cluster.Instance;
import luongld.freeswitch.cluster.queries.GetAllInstancesQuery;
import luongld.freeswitch.cluster.repositories.InstanceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllInstancesHandler implements RequestHandler<List<Instance>, GetAllInstancesQuery> {

    private final InstanceRepository instanceRepository;

    public GetAllInstancesHandler(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @Override
    public List<Instance> handle(GetAllInstancesQuery query) {
        return instanceRepository.findAll();
    }
}
