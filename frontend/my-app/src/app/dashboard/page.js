"use client";

import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import Editor from "@monaco-editor/react";
import  { useRef, useEffect } from 'react';
import dynamic from 'next/dynamic';
// import * as monaco from 'monaco-editor';

export default function Page() {

    // const socket = new SockJS("http://localhost:8080/fileSync");
    // const stompClient = new Client({
    //     webSocketFactory: () => socket,
    //     debug: (str) => {
    //         console.log(new Date(), str);
    //     },
    //     onConnect: () => {
    //         console.log("connected");
    //     }
    // });
    // stompClient.activate();

    // function handleClick() {
    //     console.log("Sending");
    //     stompClient.publish({destination: "/app/sync", body: JSON.stringify("please")});
    //     console.log("Sent");
    // }



    // function handleEditorDidMount(editor, monaco) {
    //     editorRef.current = editor;
    //     console.log(editorRef.current.getValue());
    // }

    // const monaco = dynamic(() => import('monaco-editor'), {
    //     ssr: false
    // }); 

    // window.MonacoEnvironment = {
    //     getWorker: (workerId, label) => {
    //       if (label === 'json') {
    //         return new Worker(new URL('monaco-editor/esm/vs/language/json/json.worker?worker', import.meta.url));
    //       }
    //       return new Worker(new URL('monaco-editor/esm/vs/editor/editor.worker?worker', import.meta.url));
    //     }
    // };

    // const editor = useRef(null);
    // const containerElement = useRef(null);
    // const editorMounted = useRef(false);

    // useEffect(() => {
    //     if (containerElement.current && !editorMounted.current) {
    //         editor.current = monaco.editor.create(
    //             containerElement.current,
    //             {
    //                 value: "console.log('Hello World');",
    //                 theme: "vs-dark",
    //                 language: 'javascript'
    //             }
    //         );
    //         editorMounted.current = true;
    //     }
    //     console.log(editor.current.getValue());
    // }, []);
            // {/*  */}
            // {/* <button style= {{ backgroundColor: 'white', color: 'black', marginBottom: '5px'}} onClick={handleClick}>Send to server</button> */}
    const editorRef = useRef(null);

    function handleEditorDidMount(editor, monaco) {
        // here is the editor instance
        // you can store it in `useRef` for further usage
        editorRef.current = editor;
    }
    
    return (
        <Editor onMount={handleEditorDidMount} height="50vh" width="50%" defaultLanguage="javascript" defaultValue="// some comment" />    
    );
}