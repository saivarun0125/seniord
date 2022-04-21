import * as React from "react";
import { useState } from "react";
import axios, { AxiosError } from "axios";
import { 
    Button, 
    Breadcrumb, 
    BreadcrumbItem,
    Select, 
    Label,
    Flex, 
    FlexBehavior
} from 'react-magma-dom';
import Release from "./Release";

export interface Release {
    name: String,
    id: String
}

export default function OrderOfOperationsTool() {
    const [redirect, setRedirect] = useState(false);
    const [releaseId, setReleaseId] = useState(null as null | String);
    const [options, setOptions] = useState([] as Release[]);

    const handleCreateRelease = async () => {
        setRedirect(true);
    }

    const handleSubmit = async () => {
        setRedirect(true);
        console.log(releaseId);
    }
    
    function onSelectedItemChange(changes: any) {
        setReleaseId(changes.selectedItem.id);
    }
    function optionsToString(item: Release) {
        return item ? `${item.name}` : '';
    }

    function getReleases() {
        var url = "http://localhost:8080/release";
        console.log(url);

        axios.get(url)
            .then((response) => response.data)
            .then((data) => {
                console.log(data);
                setOptions(data);
            })
            .catch((error: AxiosError) => {
                console.log(error.message);
            });
    }

    React.useEffect(() => {
        getReleases();
    }, []);


    var output = <>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem>Order of Operations Tool</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={1}>
                <Label>Create Release: </Label>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={1}>
                    <Button onClick={handleCreateRelease}> + </Button>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={1}>
                <Label>OR</Label>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={11}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Select id="existingRelease" labelText="Select Existing Release:" placeholder="Select..." items={options} itemToString={optionsToString} onSelectedItemChange={onSelectedItemChange}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Button onClick={handleSubmit}> Submit </Button>
            </Flex>
        </Flex>
    </>;

    if(redirect) {
        output = <Release id={releaseId} />;
    }

    return(output);
}