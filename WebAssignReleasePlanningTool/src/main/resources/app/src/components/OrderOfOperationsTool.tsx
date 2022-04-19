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

export interface Release {
    name: String,
    id: String
}

export default function OrderOfOperationsTool() {

    const emptyRelease: Release = {
        name: '',
        id: ''
    };

    const [isLoading, setIsLoading] = useState(false);
    const [options, setOptions] = useState([] as Release[]);
    const [selectedRelease, setSelectedRelease] = useState(emptyRelease);

    const handleSubmit = {
        //return <Release release={selectedRelease}/>;
    };

    const dummyOptions = [
        {
          label: "release #1",
          value: "25"
        },
        {
          label: "release #2",
          value: "60"
        },
        {
          label: "release #3",
          value: "45"
        }
      ];
    
    function onSelectedItemChange(changes: any) {
        setSelectedRelease(changes.selectedItem);
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
    getReleases();
    return(<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem>Order of Operations Tool</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={1}>
                <Label>Create Release: </Label>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={1}>
                    <Button> + </Button>
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
                    <Button /*onClick={handleSubmit}*/> Submit </Button>
            </Flex>
        </Flex>
    </>);
}