local event_name = argv[1];
local event_body = argv[2];

if (event_name == nil or event_name == '' or event_body == nil or event_body == '') then
    stream:write('-ERR')
    return;
end

local json = require 'resources.functions.lunajson';
local table = json.decode(event_body);

if (table == nil) then
    stream:write('-ERR')
    return;
end

local event = freeswitch.Event('CUSTOM', 'lua::inbound-trigger');
event:addHeader('Event-LuaEvent', event_name);

for key, value in pairs(table) do
    event:addHeader('Event-Variable-' .. key, value);
end

event:fire();

stream:write('+OK');