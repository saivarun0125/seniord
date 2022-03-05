import * as React from "react";
import { MouseEvent, useRef } from 'react';
import type { InteractionItem } from 'chart.js';
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
        callback: function() {
            return '';
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
      label: 'Usage',
      borderColor: 'rgb(255, 99, 132)',
      borderWidth: 2,
      fill: false,
      data: labels.map(() => Math.random()),
    },
    {
      type: 'bar' as const,
      label: 'Release Windows',
      backgroundColor: 'rgb(75, 192, 192)',
      data: dataSet,
      borderColor: 'white',
      borderWidth: 2,
    },
  ],
};


export default function ReleaseWindows() {
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
      
    <Chart
      ref={chartRef}
      type='bar'
      onClick={onClick}
      options={options}
      data={data}
    />
      </>);
}
