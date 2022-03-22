import * as React from "react";
import { useState, useRef, MouseEvent } from "react";
import axios, { AxiosError } from "axios";
import { 
    Button, 
    Breadcrumb, 
    BreadcrumbItem,
    DatePicker,
    Select, 
    Flex, 
    FlexBehavior
} from 'react-magma-dom';import type { InteractionItem } from 'chart.js';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Legend,
  PointElement,
  LineElement,
  Tooltip,
  registerables,
} from 'chart.js';
import { 
  Bar,
  Chart,
  getDatasetAtEvent,
  getElementAtEvent,
  getElementsAtEvent, } from 'react-chartjs-2';
import moment from 'moment';

ChartJS.register(
    ...registerables
  );

  ChartJS.register(
    LinearScale,
    CategoryScale,
    BarElement,
    PointElement,
    LineElement,
    Legend,
    Tooltip,
  );

export const options = {
  plugins: {
    title: {
      display: false,
      text: 'Release Windows',
    },
  },
  responsive: true,
  interaction: {
    mode: 'index' as const,
    intersect: false,
  },
  scales: {
    x: {
      stacked: true,
    },
    y: {
      stacked: true,
      ticks: {
        callback: function(t: any) {
            return t + 'k';
        }
      },
      beginAtZero: true,
    },
  },
};

const labels = ['12:00 am', 
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
];

const dataSet = [0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, .5, 0];
const randData = labels.map(() => Math.random());

const data = {
  labels,
  datasets: [
    {
      type: 'line' as const,
      label: 'clicks',
      borderColor: 'rgb(255, 99, 132)',
      borderWidth: 2,
      fill: false,
      data: labels.map(() => Math.random()),
    },
    {
      type: 'line' as const,
      label: 'autosave',
      borderColor: '#17a2b8',
      borderWidth: 2,
      fill: false,
      data: labels.map(() => Math.random()),
    },
    {
      type: 'line' as const,
      label: 'users',
      borderColor: '#5cb85c',
      borderWidth: 2,
      fill: false,
      data: labels.map(() => Math.random()),
    },
  ],
};

export default function UsageReport() {
    const [dataSource, setDataSource] = useState();
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [role, setRole] = useState("");
    const [activity, setActivity] = useState("");

    const roles = [
        {
          label: "Faculty",
          value: "FACULTY"
        },
        {
          label: "Student",
          value: "STUDENT"
        }
      ];

      const activities = [
          {
            label: "Create a Class",
            value: "CREATE_CLASS"
          },
          {
            label: "Schedule an Assignment",
            value: "SCHEDULE_ASSIGNMENT"
          }
        ];
  
  
    const handleSubmit = async () => {
        let formattedStartDate = (moment(startDate)).format('YYYY-MM-DD 00:00:00');
        let formattedEndDate = (moment(endDate)).format('YYYY-MM-DD 23:59:59');

        var url = "http://localhost:8080/duedatereport?";
        url += "startDate=" + formattedStartDate;
        url += "&endDate=" + formattedEndDate;
        console.log(url);

        axios.get(url)
            .then((response) => response.data)
            .then((data) => {
                console.log(data);
                setDataSource(data);
            });
    };

    function onSelectedRoleChange(changes: any) {
        setRole(changes.selectedItem.value);
    }

    function onSelectedActivityChange(changes: any) {
        setActivity(changes.selectedItem.value);
    }

    const printDatasetAtEvent = (dataset: InteractionItem[]) => {
        if (!dataset.length) return;
    
        const datasetIndex = dataset[0].datasetIndex;
    
        console.log(data.datasets[datasetIndex].label);
      };
    
      const printElementAtEvent = (element: InteractionItem[]) => {
        if (!element.length) return;
    
        const { datasetIndex, index } = element[0];
    
        console.log(data.labels[index], data.datasets[datasetIndex].data[index]);
      };
    
      const printElementsAtEvent = (elements: InteractionItem[]) => {
        if (!elements.length) return;
    
        console.log(elements.length);
      };
    
      const chartRef = useRef<ChartJS>(null);
    
      const onClick = (event: MouseEvent<HTMLCanvasElement>) => {
        const { current: chart } = chartRef;
    
        if (!chart) {
          return;
        }
    
        printDatasetAtEvent(getDatasetAtEvent(chart, event));
        printElementAtEvent(getElementAtEvent(chart, event));
        printElementsAtEvent(getElementsAtEvent(chart, event));
      };
  
    return (<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem>Usage Report</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <DatePicker labelText="Select Start Date:" onChange={(date)=>{
                    setStartDate(date);
                    }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <DatePicker labelText="Select End Date:" onChange={(date)=>{
                    setEndDate(date);
                }}/>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Select id="role_filter" labelText="Filter Role:" items={roles} onSelectedItemChange={onSelectedRoleChange} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Select id="activity_filter" labelText="Filter Activity:" items={activities} onSelectedItemChange={onSelectedActivityChange} />
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={8}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                    <Button onClick={handleSubmit}>Generate</Button>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={12}>
            
            <Chart
            ref={chartRef}
            type='bar'
            onClick={onClick}
            options={options}
            data={data}
            />
            </Flex>
      </Flex>
      </>
    );
  }