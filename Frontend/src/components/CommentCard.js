import { Card, Typography, Divider, Rating } from '@mui/material'
import { Box } from '@mui/system'
import moment from 'moment'

const CommentCard = ({ commentData }) => {
    return (
        <Card sx={{ p: 3, boxShadow: "0px 4px 24px rgba(0, 0, 0, 0.1)", borderRadius: "20px" }}>
            <Box>
                <Box>
                    <Rating
                        value={commentData?.score}
                        readOnly
                    />
                </Box>
                <Typography variant='p' sx={{ margin: 0, fontWeight: 300 }}><span style={{ fontWeight: 400 }}>{commentData?.customer?.name}</span> | {moment(commentData?.createdDateTime).startOf().fromNow()}</Typography>
                {/* <Button sx={{ justifySelf: "end" }}>TEKLİF VER</Button> */}
            </Box>
            <Divider sx={{ my: 2 }} />
            <Typography variant='p'>
                {commentData.comment}
            </Typography>
        </Card >
    )
}

export default CommentCard;