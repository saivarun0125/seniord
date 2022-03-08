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


export default function ReleaseWindows(props:{labels: any[], releaseWindows: any[], usage: any[]}) {
  
const randData = props.labels.map(() => Math.random());

const data = {
  labels: props.labels,
  datasets: [
    {
      type: 'line' as const,
      label: 'Usage',
      borderColor: 'rgb(255, 99, 132)',
      borderWidth: 2,
      fill: false,
      data: props.labels.map(() => randData),
    },
    {
      type: 'bar' as const,
      label: 'Priority',
      backgroundColor: 'rgb(75, 192, 192)',
      data: props.releaseWindows,
      borderColor: 'white',
      borderWidth: 2,
    },
  ],
};


  const printDatasetAtEvent = (dataset: InteractionItem[]) => {
    if (!dataset.length) return;

    const datasetIndex = dataset[0].datasetIndex;

    console.log(data.datasets[datasetIndex].label);
  };

  const printElementAtEvent = (element: InteractionItem[]) => {
    if (!element.length) return;

    const { datasetIndex, index } = element[0];

    console.log(props.labels[index], data.datasets[datasetIndex].data[index]);
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
