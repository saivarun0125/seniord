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
    items: [
        { id: 1, group: 1, className:"item-release-window", content: "Release Window #1", start: "2022-04-18 12:00 am", end: "2022-04-18 02:00 am"},
        { id: 2, group: 1, className:"item-release-window", content: "Release Window #2", start: "2022-04-19 12:00 am", end: "2022-04-19 02:00 am"},
        { id: 3, group: 2, content: "Homework 3", start: "2022-04-17 8:00 am" , end: "2022-04-17 11:59 pm" },
        { id: 4, group: 2, className:"item-exam", content: "Exam 4", start: "2022-04-16", end: "2022-04-17" },
        { id: 5, group: 2, className:"item-exam",content: "Exam #1", start: "2022-04-18 3:00 am" , end: "2022-04-18 5:00 am" },
        { id: 6, group: 2, content: "Homework 4", start: "2022-04-15", end: "2022-04-17" },
        { id: 7, group: 2, content: "Physics Quiz 1", start: "2022-04-14", end: "2022-04-16" },
        { id: 8, group: 2, content: "Quiz 2", start: "2022-04-14 8:00 am", end: "2022-04-17 5:00 pm" },
        { id: 9, group: 2, content: "Quiz 4", start: "2022-04-17 9:00 am" , end: "2022-04-17 11:30 pm" },
        { id: 10, group: 2, content: "Quiz 5", start: "2022-04-17 8:30 am" , end: "2022-04-18 11:45 pm" },
        { id: 11, group: 2, content: "Quiz 6", start: "2022-04-17 8:45 am" , end: "2022-04-19 12:00 am" },
        { id: 12, group: 2, content: "Quiz 7", start: "2022-04-18 3:15 am" , end: "2022-04-18 10:00 pm" },
        { id: 13, group: 2, content: "Quiz 8", start: "2022-04-18 3:30 am" , end: "2022-04-19 12:00 am" },
        { id: 14, group: 2, content: "Quiz 9", start: "2022-04-19 3:30 am" , end: "2022-04-21 12:00 am" },
        { id: 15, group: 2, content: "Quiz 10", start: "2022-04-19 2:30 am" , end: "2022-04-21 12:30 am" },
      ],
rawData: {
    columns : [
        { field: 'category', header: 'Category' },
        { field: 'startDate', header: 'Start Date' },
        { field: 'endDate', header: 'End Date' },
        { field: 'daysAvailable', header: 'Days Available' },
        { field: 'rosterCount', header: 'Roster Count' },
    ],
    rows : [
        {
            id: '1',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '2',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '3',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '4',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '5',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '6',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '7',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '8',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '9',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '10',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '11',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '12',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '13',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '14',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '15',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '16',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '17',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '18',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '19',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '20',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '21',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '22',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '23',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
        },
        {
            id: '24',
            category: 'Exam',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '105',
            rosterCount: '31',
        },
        {
            id: '25',
            category: 'Quiz',
            startDate: '8/9/2021 12:00:00 AM',
            endDate: '1/21/2022 11:59:00 PM',
            daysAvailable: '166',
            rosterCount: '22',
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
                    <ReleaseWindows items={dataset.items}/>
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