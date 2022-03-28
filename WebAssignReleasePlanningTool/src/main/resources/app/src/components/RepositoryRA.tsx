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
    InputSize,
    Select
} from 'react-magma-dom';

export default function RepositoryRA() {
    const [title, setTitle] = useState("");
    const [repository, setRepository] = useState("");
    const [duration, setDuration] = useState("");
    const [dependencies, setDependencies] = useState([]);
    const [action, setAction] = useState("");
    const [notes, setNotes] = useState("");

    const actionOptions = [
        {
            label: "Pull Request",
        },
        {
            label: "Revert PR",
        }
    ];

    const dummyOptions = [
        {
            label: "repo #1",
        },
        {
            label: "repo #2",
        },
        {
            label: "repo #3",
        }
    ];


    function handleSelectedAction(changes: any) {
        setAction(changes.selectedItems);
    }
    function handleSelectedDependencies(changes: any) {
        setDependencies(changes.selectedItems);
    }

    return (<>
        <Breadcrumb style={{ padding: "20px" }}>
            <BreadcrumbItem to="/">Home</BreadcrumbItem>
            <BreadcrumbItem to="/orderofoperations">Order of Operations Tool </BreadcrumbItem>
            <BreadcrumbItem to="/release#">Release # </BreadcrumbItem>
            <BreadcrumbItem>Release Action </BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{ padding: "40px" }}>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Input id="title" labelText="Title:" value={title} type={InputType.text} onChange={(e) => {
                    setTitle(e.currentTarget.value);
                }} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Input id="repository" labelText="Repository:" value={repository} type={InputType.text} onChange={(e) => {
                    setRepository(e.currentTarget.value);
                }} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Input id="duration" labelText="Duration:" value={duration} type={InputType.number} onChange={(e) => {
                    setDuration(e.currentTarget.value);
                }} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Select id="dependencyList" isMulti labelText="Dependencies:" placeholder="Select..." items={dummyOptions} onSelectedItemChange={handleSelectedDependencies} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Select id="actionList" labelText="Action:" placeholder="Select..." items={actionOptions} onSelectedItemChange={handleSelectedAction} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={4}>
                <Input labelText="Notes:" value={notes} inputSize={InputSize.large} type={InputType.text} onChange={(e) => {
                    setNotes(e.currentTarget.value);
                }} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={12}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Button>Submit</Button>
            </Flex>
        </Flex>
    </>);
}