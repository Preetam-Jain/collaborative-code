# Collaborative
A platform for real-time collaborative browser-based development.

## Design Choices
Currently trying to figure out the best method for executing code in an isolated environment, and two choices have prevailed. While Docker is the industry standard for containerized, isolated
application environments, the EngineerMan on YouTube has explained that his code execution engine, Piston, uses LXC instead of Docker to execute code, citing that while Docker was first used,
it was much too slow for their needs. However, most sources on the web argue that Docker is actually faster, has a faster startup time, and the only downside is precision resource management that
LXC offers. For example: https://blog.purestorage.com/purely-educational/docker-vs-lxc/
