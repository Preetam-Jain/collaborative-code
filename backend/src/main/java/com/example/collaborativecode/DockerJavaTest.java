package com.example.collaborativecode;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.DefaultDockerClientConfig;

import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

import com.github.dockerjava.transport.DockerHttpClient;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.time.Duration;

public class DockerJavaTest {
    public static void main(String[] args) {
        try (PipedOutputStream outputStream = new PipedOutputStream();
             PipedInputStream inputStream = new PipedInputStream(outputStream)) {

            DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
            DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

            DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
            
            Thread containerThreadOne = new Thread(() -> {
                String image = "test-print";
                
                Ports.Binding portsBinding = Ports.Binding.bindPort(5005);
                ExposedPort exposedPort = ExposedPort.tcp(8000);
                PortBinding portBinding = new PortBinding(portsBinding, exposedPort);

                CreateContainerResponse container = dockerClient.createContainerCmd(image)
                    .withHostConfig(HostConfig.newHostConfig().withAutoRemove(true).withPortBindings(portBinding))
                    .exec();

                String containerID = container.getId();
                System.out.println("About to start container: " + containerID);
                dockerClient.startContainerCmd(containerID).exec();
                System.out.println("Running container");

                dockerClient.attachContainerCmd(containerID)
                    .withLogs(false)
                    .withStdErr(true)
                    .withStdOut(true)
                    .withFollowStream(true)
                    .exec(new ResultCallback.Adapter<>() {
                        @Override
                        public void onNext(Frame object) {
                            System.out.println(object);  //for example
                            try {
                                outputStream.write(object.getPayload());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                );
            });

            containerThreadOne.start();

            try {
                Thread.sleep(30000); 
                containerThreadOne.join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread interrupted.");
            }

            // System.out.println("Printing container output!");
            // int inputByte;
            // try {
            //     while ((inputByte = inputStream.read()) != -1) {
            //         System.out.print((char) inputByte);
            //     }
            // } catch (IOException ioException) {
            //     System.out.println("Finished reading from container output");
            // } 

            System.out.println("Exiting!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createContainerThread() {
        
    }

}
