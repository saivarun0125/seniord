import * as React from "react";
import { useState } from "react";
import { Row, Col } from "react-bootstrap";
import { 
    Button, 
    Input, 
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

export default function DueDateReportMagma() {
    const [validated, setValidated] = useState(false);
    const [activeTab, setActiveTab] = useState(0);
    const [errorMessage, setErrorMessage] = useState("");
    const [selectedItem, updateSelectedItem] = React.useState('');

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
  
  
    const handleSubmit = (event:  React.FormEvent) => {
      setValidated(true);
    };

    function onSelectedItemChange(changes: any) {
      updateSelectedItem(changes.selectedItem);
      console.log(changes.selectedItem);
    }
  
    return (
    <Container>
        <Form 
            header="Due Date Report"
            errorMessage={errorMessage}
            onSubmit={handleSubmit}
            actions={
            <Row style={{padding: "1rem!important"}}>
                <Col md={2}>
                    <Button>Generate</Button>
                </Col>
            </Row>
            }
        >
            <Row className="mb-3">
                <Col md={2}>
                    <Input labelText="Start" />
                </Col>
            </Row>
            <Row>
                <Col md={2}>
                    <Input labelText="End" />
                </Col>
            </Row>
            <Row>
                <Col md={2}>
                    <Input labelText="Duration" />
                </Col>
                <Col md={2}>
                    <Select id="release" labelText="Release" items={options} onSelectedItemChange={onSelectedItemChange} />
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
                    <div>Release Windows</div>
                    </TabPanel>
                    <TabPanel>
                    <div>Raw Data</div>
                    </TabPanel>
                </TabPanelsContainer>
            </TabsContainer>
      </Container>
    );
  }