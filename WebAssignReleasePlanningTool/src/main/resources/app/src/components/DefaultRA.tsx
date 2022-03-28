import * as React from "react";
import { useState } from "react";
import { 
    Button, 
    Breadcrumb, 
    BreadcrumbItem,
    Input, 
    InputType,
    Flex, 
    FlexBehavior,
    InputSize
} from 'react-magma-dom';

export default function DefaultRA() {
    const [title, setTitle] = useState("");
    const [duration, setDuration] = useState("");
    const [notes, setNotes] = useState("");

    return(<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/">Home</BreadcrumbItem>
            <BreadcrumbItem to="/orderofoperations">Order of Operations Tool </BreadcrumbItem>
            <BreadcrumbItem to="/release#">Release # </BreadcrumbItem>
            <BreadcrumbItem>Release Action </BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Input labelText="Title:" value={title} type={InputType.text} onChange={(e)=>{
                        setTitle(e.currentTarget.value);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Input labelText="Duration:" value={duration} type={InputType.number} onChange={(e)=>{
                        setDuration(e.currentTarget.value);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={4}>
                    <Input labelText="Notes:" value={notes} inputSize={InputSize.large} type={InputType.text} onChange={(e)=>{
                        setNotes(e.currentTarget.value);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={12}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Button>Submit</Button>
            </Flex>
        </Flex>
    </>);
}