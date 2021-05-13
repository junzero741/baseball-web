import { useRef } from "react";
import styled from "styled-components";

const RecordOfPlayer = ({ results }) => {
	const ballCnt = useRef(0);
	const strikeCnt = useRef(0);
	strikeCnt.current = 0;
	ballCnt.current = 0;
	let consequnece = "진행중";

	const getPlayerConsquence = (pitchType) => {
		const consequence = {
			FOUR_BALL: "진루!",
			THREE_STRIKE: "아웃!",
			HIT: "안타!",
		};
		return consequence[pitchType];
	};

	const finalResult = (result) => {
		if (ballCnt.current === 4) {
			consequnece = getPlayerConsquence("FOUR_BALL");
		} else if (strikeCnt.current === 3) {
			consequnece = getPlayerConsquence("THREE_STRIKE");
		} else if (result === "H") {
			consequnece = getPlayerConsquence("HIT");
		}
	};
	const recordList = results.map((result, idx) => {
		const pitchType = {
			B: "볼",
			S: "스트라이크",
			H: "안타",
		};

		if (result === "B") ballCnt.current += 1;
		else if (result === "S") strikeCnt.current += 1;
		if (idx === results.length - 1) finalResult(result);

		return (
			<Record key={10 * idx}>
				<Idx>{idx + 1}</Idx> <Type>{pitchType[result]}</Type>
				<Counter>
					<BallCnt>{ballCnt.current}</BallCnt>
					<StrikeCnt>{strikeCnt.current}</StrikeCnt>
				</Counter>
			</Record>
		);
	});

	return (
		<StyledRecordOfPlayer>
			<Result>{consequnece}</Result>
			{recordList.reverse()}
		</StyledRecordOfPlayer>
	);
};

export default RecordOfPlayer;

const StyledRecordOfPlayer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;
const Result = styled.div`
	color: #488b9b;
	margin-left: 4rem;
	padding: 0.3rem 0;
`;
const Record = styled.div`
	width: 100%;
	display: grid;
	grid-template-columns: 0.1fr 0.6fr 0.5fr;
	padding: 0.3rem 0;
`;
const Idx = styled.span`
	text-align: center;
	font-size: 16px;
	width: 1rem;
	color: black;
	background-color: white;
	border-radius: 25px;
`;
const Type = styled.span`
	justify-self: center;
	text-align: center;
	width: 6rem;
`;

const Counter = styled.span`
	text-align: center;
	color: gray;
`;
const BallCnt = styled.span`
	padding: 0 0.5rem;
	&::before {
		content: "B";
	}
`;
const StrikeCnt = styled.span`
	&::before {
		content: "S";
	}
`;
