import * as React from "react";
import { useState } from "react";
import axios, { AxiosError } from "axios";
import { 
    Button, 
    ButtonSize ,
    Breadcrumb, 
    BreadcrumbItem,
    Checkbox,
    InputType,
    Input,
    Flex, 
    FlexBehavior,
    FlexAlignContent,
    Modal,
    RadioGroup, 
    Radio,
    Tab,
    Tabs,
    TabPanel,
    TabPanelsContainer,
    TabsContainer
} from 'react-magma-dom';
import { ModeIcon, DeleteForeverIcon } from 'react-magma-icons';
import { List, arrayMove } from "react-movable";
import ReleaseAction from "./ReleaseAction";

interface Release {
    name: string;
    duration: number;
}

const blankRelease: Release = {
    name: "",
    duration: 0
}

    
const actions = [
    {
      label: "Clear Cache",
      id: "1"
    },
    {
      label: "PR for python lib",
      id: "2"
    },
    {
      label: "Create Table",
      id: "3"
    },
    {
      label: "Update Table",
      id: "4"
    }
  ];

export default function Release(releaseProps: {id: String | null}) {
    const [redirect, setRedirect] = useState(false);
    const [actionType, setActionType] = useState("defaultAction");
    const [release, setRelease] = useState(blankRelease);
    const [releaseActionId, setReleaseActionId] = useState(null as null | String);
    const [releaseActions, setReleaseActions] = useState(actions);
    const [rollbackActions, setRollbackActions] = useState(actions);
    const [showModal, setShowModal] = React.useState(false);

    React.useEffect(() => {
        console.log("Release-oncreate");
        if(releaseProps.id) {
            const url = "http://localhost:8080/release/" + releaseProps.id;
            console.log(url);

            axios.get(url)
                .then((response) => response.data)
                .then((release) => {
                    console.log(release);
                    setRelease({
                        name: release.name,
                        duration: release.duration
                    });
                })
                .catch((error: AxiosError) => {
                    console.log(error.message);
                });
        }
    }, []);

    
    React.useEffect(() => {
        console.log("useEffect");
        console.log(releaseActionId);
        // make API call to fetch release action object
      }, [releaseActionId]);
    
    function ToDo(props: {value: any, props: any}) {
        const [completed, setCompleted] = useState(false);
        const [labelStyle, setLabelStyle] = useState({});
    
        const editAction = (evt: any) => {
          setReleaseActionId(props.value.id);
        };
        
        return(
            <div className="Todo" {...props.props}>
                
        <Flex behavior={FlexBehavior.container} spacing={2}>
            <Flex behavior={FlexBehavior.item} xs={4} >
                <Checkbox labelText={props.value.label} labelStyle={labelStyle} onChange={() => {
                    if(completed) {
                        setCompleted(false);
                        setLabelStyle({});
                    }
                    else {
                        setCompleted(true);
                        setLabelStyle({textDecoration: 'line-through'});
                    }
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8} alignContent={FlexAlignContent.flexStart} >
              <div className="Todo-buttons">
                  <Button onClick={editAction} size={ButtonSize.small}>
                    <ModeIcon title={"edit"} />                
                    </Button>
                <Button onClick={editAction} size={ButtonSize.small}>
                    <DeleteForeverIcon title={"delete"} />     
                </Button>
              </div>
              </Flex></Flex>
            </div>);
    }

    var output = (<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem to="/orderofoperationstool">Order of Operations Tool</BreadcrumbItem>
            <BreadcrumbItem>Release</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={3}>
                <Input labelText="Name" type={InputType.text} value={release.name}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Input  labelText="Duration" type={InputType.number} value={release.duration}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={7}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={12}>
                <TabsContainer activeIndex={0}>
                    <Tabs>
                        <Tab>Release Plan</Tab>
                        <Tab>Rollback Plan</Tab>
                    </Tabs>
                    <TabPanelsContainer>
                        <TabPanel>
                            <List
                            values={releaseActions}
                            onChange={({ oldIndex, newIndex }) =>
                                setReleaseActions(arrayMove(releaseActions, oldIndex, newIndex))
                            }
                            renderList={({ children, props }) => <div {...props}>{children}</div>}
                            renderItem={({ value, props }) => <ToDo props={props} value={value}/>}
                            />
                        </TabPanel>
                        <TabPanel>
                            <List
                            values={rollbackActions}
                            onChange={({ oldIndex, newIndex }) =>
                                setRollbackActions(arrayMove(rollbackActions, oldIndex, newIndex))
                            }
                            renderList={({ children, props }) => <div {...props}>{children}</div>}
                            renderItem={({ value, props }) => <ToDo props={props} value={value}/>}
                            />
                        </TabPanel>
                    </TabPanelsContainer>
                </TabsContainer>
            </Flex>
              
            <Flex behavior={FlexBehavior.item} xs={4} >
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8} >
                <Modal
                    header="Add Release Action"
                    onClose={() => setShowModal(false)}
                    isOpen={showModal}
                >
                    <RadioGroup id="basicGroup" name="basic" value="defaultAction" onChange={(e: any) => setActionType(e.target.value)}>
                        <Radio id="radio1" labelText="Default" value="defaultAction"/>
                        <Radio id="radio2" labelText="Repository Action" value="repositoryAction" />
                    </RadioGroup>
                    <Button onClick={() => setRedirect(true)}>
                        OK    
                    </Button>
                </Modal>
                <Button size={ButtonSize.small} onClick={() => setShowModal(true)}>
                    Add Action     
                </Button>
            </Flex>
              
            <Flex behavior={FlexBehavior.item} xs={7} >
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={5} >
                <Button>
                    Submit     
                </Button>
            </Flex>
        </Flex>
    </>);

    if(redirect) {
        output = <ReleaseAction id={releaseActionId} type={actionType}/>;
    }

    return (output);
}