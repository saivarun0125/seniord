import * as React from "react";
import { useState } from "react";
import axios, { AxiosError } from "axios";

import { Row, Col } from "react-bootstrap";
import { 
    Button, 
    ButtonType,
    DatePicker,
    Input, 
    InputType,
    Container, 
    Form,
    Select, 
    TabsAlignment,
    TabsContainer,
    Tabs,
    Tab,
    TabPanelsContainer,
    TabPanel, 
} from 'react-magma-dom';

import RawData from "./RawData";
import ReleaseWindows from "./ReleaseWindows";

export default function DueDateReportMagma() {
    const [duration, setDuration] = useState(0);
    const [errorMessage, setErrorMessage] = useState("");

    const options = [
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
  
  
    const handleSubmit = async () => {
        const url = "http://localhost:8080/assignment/Acid%20Basics";
        axios.get(url)
            .then((response) => response.data)
            .then((data) => {
                console.log(data);
            });
    };

    function onSelectedItemChange(changes: any) {
        setDuration(changes.selectedItem.value);
    }
  
    return (
    <Container>
        <Form 
            header="Due Date Report"
            errorMessage={errorMessage}
            actions={
            <>
            </>
            }
        >
            <Row className="mb-3">
                <Col md={2}>
                    <DatePicker labelText="Start" />
                </Col>
            </Row>
            <Row>
                <Col md={2}>
                    <DatePicker labelText="End" />
                </Col>
            </Row>
            <Row>
                <Col md={2}>
                    <Input labelText="Duration" type={InputType.number} value={duration}/>
                </Col>
                <Col md={2}>
                    <Select id="release" labelText="Release" items={options} onSelectedItemChange={onSelectedItemChange} />
                </Col>
            </Row>
            <Row style={{padding: "1rem!important"}}>
                <Col md={2}>
                    <Button onClick={handleSubmit}>Generate</Button>
                </Col>
            </Row>
        </Form>
            <TabsContainer activeIndex={0} TabsAlignment={TabsAlignment.center}>
                <Tabs aria-label="Sample Tabs">
                    <Tab>Release Windows</Tab>
                    <Tab>Raw Data</Tab>
                </Tabs>
                <TabPanelsContainer>
                    <TabPanel>
                    <ReleaseWindows />
                    </TabPanel>
                    <TabPanel>
                    <RawData />
                    </TabPanel>
                </TabPanelsContainer>
            </TabsContainer>
      </Container>
    );
  }