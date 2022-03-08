import * as React from "react";
import { useState } from "react";
import axios, { AxiosError } from "axios";
import { 
    Button, 
    Breadcrumb, 
    BreadcrumbItem,
    DatePicker,
    Input, 
    InputType,
    Select, 
    TabsAlignment,
    TabsContainer,
    Tabs,
    Tab,
    TabPanelsContainer,
    TabPanel, 
    Flex, 
    FlexBehavior
} from 'react-magma-dom';

import RawData from "./RawData";
import ReleaseWindows from "./ReleaseWindows";

const dataset = {
    chart: {
    labels : ['12:00 am', 
    '1:00 am', 
    '2:00 am', 
    '3:00 am', 
    '4:00 am', 
    '5:00 am', 
    '6:00 am-7:00 am', 
    '8:00 am', 
    '9:00 am', 
    '10:00 am', 
    '11:00 am', 
    '12:00 pm', 
    '1:00 pm', 
    '2:00 pm', 
    '3:00 pm', 
    '4:00 pm', 
    '5:00 pm', 
    '6:00 pm', 
    '7:00 pm', 
    '8:00 pm', 
    '9:00 pm-10:00 pm', 
    '11:00 pm',
    ],
releaseWindows : [0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
usage : [0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1.5, 2, 1, 0, 5, 0]
},
rawData: {
    columns : [
        { field: 'col1', header: 'Col 1' },
        { field: 'col2', header: 'Col 2' },
        { field: 'col3', header: 'Col 3' },
        { field: 'col4', header: 'Col 4' },
    ],
    rows : [
        {
            id: 1,
            col1: '1 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 2,
            col1: '2 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 3,
            col1: '3 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 4,
            col1: '4 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 5,
            col1: '5 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 6,
            col1: '6 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 7,
            col1: '7  Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 8,
            col1: '8 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 9,
            col1: '9 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 10,
            col1: '10 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 11,
            col1: '11 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 12,
            col1: '12 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 13,
            col1: '13 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 14,
            col1: '14 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 15,
            col1: '15 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 16,
            col1: '16 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 17,
            col1: '17 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 18,
            col1: '18 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 19,
            col1: '19 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 20,
            col1: '20 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 21,
            col1: '21 Lorem ipsum dolor sit amet consectetur',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 22,
            col1: '22 Lorem ipsum dolor sit amet',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 23,
            col1: '23 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 24,
            col1: '24 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
        {
            id: 25,
            col1: '25 Lorem ipsum dolor',
            col2: 'Lorem ipsum dolor',
            col3: 'Lorem ipsum dolor',
            col4: 'Lorem ipsum',
        },
    ]
}};

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
  
    return (<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem>Due Date Report</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <DatePicker labelText="Start" />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <DatePicker labelText="End" />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Input labelText="Duration" type={InputType.number} value={duration}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Select id="release" labelText="Release" items={options} onSelectedItemChange={onSelectedItemChange} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Button onClick={handleSubmit}>Generate</Button>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={12}>
            <TabsContainer activeIndex={0} TabsAlignment={TabsAlignment.center}>
                <Tabs aria-label="Sample Tabs">
                    <Tab>Release Windows</Tab>
                    <Tab>Raw Data</Tab>
                </Tabs>
                <TabPanelsContainer>
                    <TabPanel>
                    <ReleaseWindows labels={dataset.chart.labels} releaseWindows={dataset.chart.releaseWindows} usage={dataset.chart.usage}/>
                    </TabPanel>
                    <TabPanel>
                    <RawData columns={dataset.rawData.columns} rows={dataset.rawData.rows}/>
                    </TabPanel>
                </TabPanelsContainer>
            </TabsContainer>
            </Flex>
      </Flex>
      </>
    );
  }