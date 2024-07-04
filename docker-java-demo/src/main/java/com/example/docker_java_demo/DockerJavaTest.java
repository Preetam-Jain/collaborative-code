package com.example.docker_java_demo;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.HostConfig;

import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.DefaultDockerClientConfig;

import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

import com.github.dockerjava.transport.DockerHttpClient;

import java.time.Duration;

public class DockerJavaTest {
    public static void main(String[] args) {
        try {
            DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
            DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

            DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
            
            String image = "test-print";
            CreateContainerResponse container = dockerClient.createContainerCmd(image)
                .withHostConfig(HostConfig.newHostConfig().withAutoRemove(true))
                .exec();

            String containerID = container.getId();
            System.out.println("About to start container: " + containerID);
            dockerClient.startContainerCmd(containerID).exec();
            System.out.println("Running container");
            Thread.sleep(20000);
            dockerClient.stopContainerCmd(containerID).exec();
            dockerClient.removeContainerCmd(containerID).exec();
            System.out.println("Container stopped and removed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
