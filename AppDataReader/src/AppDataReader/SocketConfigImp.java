package AppDataReader;

import java.util.Map;

public class SocketConfigImp implements SocketConfig{

    private final JSONReader jsonReader;
    private final Map<Object, Object> configMap;

    public SocketConfigImp(){
        this.jsonReader = new JSONReader(new ConfigPath().get());

        this.configMap = (Map) this.jsonReader.getMap(AppDataEnum.SocketConfigurations);
    }

    @Override
    public String getHost() {
        return (String) this.configMap.get(AppDataEnum.Host.name());
    }

    @Override
    public int getPort() {
        return Integer.parseInt((String) this.configMap.get(AppDataEnum.Port.name()));
    }

}
